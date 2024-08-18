package ru.boris.psychologist.notebook.service.tg;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.boris.psychologist.notebook.api.mapper.tg.message.history.PatientMessageHistoryService;
import ru.boris.psychologist.notebook.api.service.tg.DefaultResponseService;
import ru.boris.psychologist.notebook.api.service.tg.DescriptionService;
import ru.boris.psychologist.notebook.api.service.tg.MakeAppointmentService;
import ru.boris.psychologist.notebook.api.service.tg.PhoneNumberService;
import ru.boris.psychologist.notebook.api.service.tg.UpdateHandler;
import ru.boris.psychologist.notebook.api.service.tg.callback.CallbackQueryHandlers;
import ru.boris.psychologist.notebook.api.service.tg.command.BotCommandHandler;
import ru.boris.psychologist.notebook.dto.tg.CallbackQueryDto;
import ru.boris.psychologist.notebook.dto.tg.InlineKeyboardButtonDto;
import ru.boris.psychologist.notebook.dto.tg.InlineKeyboardMarkupDto;
import ru.boris.psychologist.notebook.dto.tg.MessageDto;
import ru.boris.psychologist.notebook.dto.tg.MessageEntityDto;
import ru.boris.psychologist.notebook.dto.tg.ResponseDto;
import ru.boris.psychologist.notebook.dto.tg.UpdateDto;
import ru.boris.psychologist.notebook.dto.tg.UserDto;
import ru.boris.psychologist.notebook.dto.tg.callback.CallbackTypes;
import ru.boris.psychologist.notebook.dto.tg.command.BotCommands;
import ru.boris.psychologist.notebook.model.entity.PatientMessageHistoryEntity;
import ru.boris.psychologist.notebook.model.entity.PatientMessageHistoryType;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UpdateHandlerImpl implements UpdateHandler {

    private final PhoneNumberService phoneNumberService;

    private final DescriptionService descriptionService;

    private final DefaultResponseService defaultResponseService;

    private final Map<BotCommands, BotCommandHandler> botCommandHandlers;

    private final Map<CallbackTypes, CallbackQueryHandlers> callbackQueryHandlers;

    private final PatientMessageHistoryService patientMessageHistoryService;

    private final MakeAppointmentService makeAppointmentService;

    @Override
    public Optional<ResponseDto> handle(UpdateDto updateDto) {
        boolean messageIsPresent = Objects.nonNull(updateDto.getMessage());
        if (messageIsPresent) {
            return handleMessage(updateDto);
        }

        CallbackQueryDto callbackQuery = updateDto.getCallbackQuery();
        boolean callbackQueryIsPresent = Objects.nonNull(callbackQuery);

        if (callbackQueryIsPresent) {
            return handleCallbackQuery(updateDto, callbackQuery);
        }

        return defaultResponseService.createResponse(updateDto);
    }

    private Optional<ResponseDto> handleMessage(UpdateDto updateDto) {
        Integer updateId = updateDto.getUpdateId();

        List<MessageEntityDto> messageEntities = getMessageEntities(updateDto);
        List<MessageEntityDto> messageEntitiesWithCommand = getMessageEntitiesWithCommand(messageEntities);
        int commandSize = messageEntitiesWithCommand.size();

        if (commandSize != 0) {

            if (commandSize > 1) {
                log.error("В сообщение больше одной команды. id: {}", updateId);
                return defaultResponseService.createResponse(updateDto);
            }

            return commandHandler(updateDto, messageEntitiesWithCommand);
        }

        Optional<Long> patientIdOpt = Optional.ofNullable(updateDto.getMessage())
                .map(MessageDto::getFrom)
                .map(UserDto::getId);

        if (patientIdOpt.isEmpty()) {
            log.debug("Идентификатор пациента не найден. updateId: {}", updateId);
            return defaultResponseService.createResponse(updateDto);
        }

        Long patientId = patientIdOpt.get();
        Optional<PatientMessageHistoryType> historyType = patientMessageHistoryService
                .findLastMessageByPatientId(patientId)
                .map(PatientMessageHistoryEntity::getHistoryType);

        List<MessageEntityDto> messageEntitiesWithPhoneNumber = getMessageEntitiesWithPhoneNumber(messageEntities);
        int phoneNumbersSize = messageEntitiesWithPhoneNumber.size();

        if (phoneNumbersSize != 0) {
            if (phoneNumbersSize > 1) {
                log.error("В сообщение больше одного номера. id: {}", updateId);
                return defaultResponseService.createResponse(updateDto);
            }

            return phoneNumbersHandler(historyType, patientId, updateDto, messageEntitiesWithPhoneNumber);
        }

        return messageHandler(historyType, patientId, updateDto);
    }

    private Optional<ResponseDto> messageHandler(
            Optional<PatientMessageHistoryType> historyTypeOpt,
            Long patientId,
            UpdateDto updateDto) {
        Integer updateId = updateDto.getUpdateId();

        if (historyTypeOpt.isEmpty()) {
            return defaultResponseService.createResponse(updateDto);
        }

        PatientMessageHistoryType historyType = historyTypeOpt.get();

        if (PatientMessageHistoryType.ADD_DESCRIPTION.equals(historyType)) {
            Optional<ResponseDto> responseDto = descriptionService.saveDescription(updateDto);
            patientMessageHistoryService.saveAddedDescriptionHistory(patientId, updateId);
            return responseDto;
        }

        if (PatientMessageHistoryType.REQUEST_FOR_APPOINTMENT.equals(historyType)) {
            Optional<ResponseDto> responseDto = makeAppointmentService.saveNameForAppointment(updateDto);
            patientMessageHistoryService.saveAddedDescriptionHistory(patientId, updateId);
            return responseDto;
        }

        return defaultResponseService.createResponse(updateDto);
    }

    private Optional<ResponseDto> phoneNumbersHandler(
            Optional<PatientMessageHistoryType> historyType,
            Long patientId, UpdateDto updateDto,
            List<MessageEntityDto> messageEntitiesWithPhoneNumber) {

        Integer updateId = updateDto.getUpdateId();
        Optional<MessageEntityDto> messageEntityDto = messageEntitiesWithPhoneNumber.stream()
                .findFirst();

        if (messageEntityDto.isEmpty()) {
            log.error("В сообщении нет ни одного номера id: " + updateId);
            return defaultResponseService.createResponse(updateDto);
        }

        if (historyType.isPresent() && PatientMessageHistoryType.ADD_PHONE_NUMBER.equals(historyType.get())) {
            Optional<ResponseDto> responseDto = phoneNumberService.saveNumber(updateDto, messageEntityDto.get());
            patientMessageHistoryService.saveAddedPhoneNumberHistory(patientId, updateId);
            return responseDto;
        }

        return defaultResponseService.createResponse(updateDto);
    }

    private Optional<ResponseDto> commandHandler(UpdateDto updateDto, List<MessageEntityDto> messageEntitiesWithCommand) {
        Integer updateId = updateDto.getUpdateId();

        MessageEntityDto messageEntityDto = messageEntitiesWithCommand.stream().findFirst()
                .orElseThrow(() -> new RuntimeException("В сообщении нет ни одной команды. id: " + updateId));

        String command = messageEntityDto.getText();
        BotCommands botCommands = BotCommands.getBotCommands(command);

        return Optional.ofNullable(botCommandHandlers.get(botCommands))
                .flatMap(handler -> handler.handle(updateDto));
    }

    private Optional<ResponseDto> handleCallbackQuery(UpdateDto updateDto, CallbackQueryDto callbackQuery) {
        Integer updateId = updateDto.getUpdateId();

        List<List<InlineKeyboardButtonDto>> keyboardButtonsList = getKeyboardButtonsList(callbackQuery);
        List<InlineKeyboardButtonDto> keyboardButtons = getKeyboardButtons(keyboardButtonsList);

        int keyboardButtonsSize = keyboardButtons.size();

        if (keyboardButtonsSize == 0) {
            log.error("В сообщение нет ответа на запрос. id: {}", updateId);
            return defaultResponseService.createResponse(updateDto);
        }

        CallbackTypes callbackType = CallbackTypes.getCallbackType(callbackQuery.getData());

        return Optional.ofNullable(callbackQueryHandlers.get(callbackType))
                .flatMap(handler -> handler.handle(updateDto));
    }

    private List<MessageEntityDto> getMessageEntitiesWithPhoneNumber(List<MessageEntityDto> messageEntities) {
        return messageEntities.stream()
                .filter(dto -> "phone_number".equals(dto.getType()))
                .toList();
    }

    private List<InlineKeyboardButtonDto> getKeyboardButtons(List<List<InlineKeyboardButtonDto>> keyboardButtonsList) {
        return keyboardButtonsList.stream()
                .flatMap(List::stream)
                .toList();
    }

    private List<List<InlineKeyboardButtonDto>> getKeyboardButtonsList(CallbackQueryDto callbackQuery) {
        return Optional.ofNullable(callbackQuery.getMessage())
                .map(MessageDto::getReplyMarkup)
                .map(InlineKeyboardMarkupDto::getKeyboard)
                .orElse(Collections.emptyList());
    }

    private List<MessageEntityDto> getMessageEntitiesWithCommand(List<MessageEntityDto> messageEntities) {
        return messageEntities.stream()
                .filter(dto -> "bot_command".equals(dto.getType()))
                .toList();
    }

    private List<MessageEntityDto> getMessageEntities(UpdateDto updateDto) {
        return Optional.ofNullable(updateDto.getMessage())
                .map(MessageDto::getEntities)
                .orElse(Collections.emptyList());
    }
}
