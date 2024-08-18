package ru.boris.psychologist.notebook.service.tg.mesage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.boris.psychologist.notebook.api.mapper.history.ClientMessageHistoryService;
import ru.boris.psychologist.notebook.api.service.tg.DefaultResponseService;
import ru.boris.psychologist.notebook.api.service.tg.DescriptionService;
import ru.boris.psychologist.notebook.api.service.tg.MakeAppointmentService;
import ru.boris.psychologist.notebook.api.service.tg.PhoneNumberService;
import ru.boris.psychologist.notebook.api.service.tg.command.BotCommandHandler;
import ru.boris.psychologist.notebook.api.service.tg.command.TgCommandService;
import ru.boris.psychologist.notebook.api.service.tg.message.TgMessageService;
import ru.boris.psychologist.notebook.dto.tg.CallbackQueryDto;
import ru.boris.psychologist.notebook.dto.tg.MessageDto;
import ru.boris.psychologist.notebook.dto.tg.MessageEntityDto;
import ru.boris.psychologist.notebook.dto.tg.ResponseDto;
import ru.boris.psychologist.notebook.dto.tg.UpdateDto;
import ru.boris.psychologist.notebook.dto.tg.UserDto;
import ru.boris.psychologist.notebook.dto.tg.command.BotCommands;
import ru.boris.psychologist.notebook.dto.tg.step.ClientMessageStepType;
import ru.boris.psychologist.notebook.entity.step.ClientMessageStepEntity;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Реализация сервиса для работы с message.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TgMessageServiceImpl implements TgMessageService {

    private final PhoneNumberService phoneNumberService;

    private final DescriptionService descriptionService;

    private final MakeAppointmentService makeAppointmentService;

    private final DefaultResponseService defaultResponseService;

    private final ClientMessageHistoryService clientMessageHistoryService;

    @Override
    public Optional<ResponseDto> handleMessage(UpdateDto updateDto) {
        Integer updateId = updateDto.getUpdateId();

        Optional<Long> clientIdOpt = Optional.ofNullable(updateDto.getMessage())
                .map(MessageDto::getFrom)
                .map(UserDto::getId);

        if (clientIdOpt.isEmpty()) {
            log.debug("Идентификатор пациента не найден. updateId: {}", updateId);
            return defaultResponseService.createResponse(updateDto);
        }

        Long clientId = clientIdOpt.get();
        Optional<ClientMessageStepType> historyType = clientMessageHistoryService
                .findLastMessageByClientId(clientId)
                .map(ClientMessageStepEntity::getStep);

        List<MessageEntityDto> messageEntitiesWithPhoneNumber = getMessageEntitiesWithPhoneNumber(messageEntities);
        int phoneNumbersSize = messageEntitiesWithPhoneNumber.size();

        if (phoneNumbersSize != 0) {
            if (phoneNumbersSize > 1) {
                log.error("В сообщение больше одного номера. id: {}", updateId);
                return defaultResponseService.createResponse(updateDto);
            }

            return phoneNumbersHandler(historyType, clientId, updateDto, messageEntitiesWithPhoneNumber);
        }

        return messageHandler(historyType, clientId, updateDto);
    }

    private Optional<ResponseDto> messageHandler(
            Optional<ClientMessageStepType> historyTypeOpt,
            Long clientId,
            UpdateDto updateDto) {
        Integer updateId = updateDto.getUpdateId();

        if (historyTypeOpt.isEmpty()) {
            return defaultResponseService.createResponse(updateDto);
        }

        ClientMessageStepType historyType = historyTypeOpt.get();

        if (ClientMessageStepType.ADD_DESCRIPTION.equals(historyType)) {
            Optional<ResponseDto> responseDto = descriptionService.saveDescription(updateDto);
            clientMessageHistoryService.saveAddedDescriptionHistory(clientId, updateId);
            return responseDto;
        }

        if (ClientMessageStepType.REQUEST_FOR_APPOINTMENT.equals(historyType)) {
            Optional<ResponseDto> responseDto = makeAppointmentService.saveNameForAppointment(updateDto);
            clientMessageHistoryService.saveAddedDescriptionHistory(clientId, updateId);
            return responseDto;
        }

        return defaultResponseService.createResponse(updateDto);
    }

    private Optional<ResponseDto> phoneNumbersHandler(
            Optional<ClientMessageStepType> historyType,
            Long clientId, UpdateDto updateDto,
            List<MessageEntityDto> messageEntitiesWithPhoneNumber) {

        Integer updateId = updateDto.getUpdateId();
        Optional<MessageEntityDto> messageEntityDto = messageEntitiesWithPhoneNumber.stream()
                .findFirst();

        if (messageEntityDto.isEmpty()) {
            log.error("В сообщении нет ни одного номера id: " + updateId);
            return defaultResponseService.createResponse(updateDto);
        }

        if (historyType.isPresent() && ClientMessageStepType.ADD_PHONE_NUMBER.equals(historyType.get())) {
            Optional<ResponseDto> responseDto = phoneNumberService.saveNumber(updateDto, messageEntityDto.get());
            clientMessageHistoryService.saveAddedPhoneNumberHistory(clientId, updateId);
            return responseDto;
        }

        return defaultResponseService.createResponse(updateDto);
    }

    private List<MessageEntityDto> getMessageEntitiesWithPhoneNumber(List<MessageEntityDto> messageEntities) {
        return messageEntities.stream()
                .filter(dto -> "phone_number".equals(dto.getType()))
                .toList();
    }
}
