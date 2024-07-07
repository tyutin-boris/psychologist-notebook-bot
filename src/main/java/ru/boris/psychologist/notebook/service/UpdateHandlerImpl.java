package ru.boris.psychologist.notebook.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.boris.psychologist.notebook.api.service.DefaultResponseService;
import ru.boris.psychologist.notebook.api.service.UpdateHandler;
import ru.boris.psychologist.notebook.api.service.callback.CallbackQueryHandlers;
import ru.boris.psychologist.notebook.api.service.command.BotCommandHandler;
import ru.boris.psychologist.notebook.dto.CallbackQueryDto;
import ru.boris.psychologist.notebook.dto.InlineKeyboardButtonDto;
import ru.boris.psychologist.notebook.dto.InlineKeyboardMarkupDto;
import ru.boris.psychologist.notebook.dto.MessageDto;
import ru.boris.psychologist.notebook.dto.MessageEntityDto;
import ru.boris.psychologist.notebook.dto.ResponseDto;
import ru.boris.psychologist.notebook.dto.UpdateDto;
import ru.boris.psychologist.notebook.dto.callback.CallbackTypes;
import ru.boris.psychologist.notebook.dto.command.BotCommands;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UpdateHandlerImpl implements UpdateHandler {

    private final DefaultResponseService defaultResponseService;

    private final Map<BotCommands, BotCommandHandler> botCommandHandlers;

    private final Map<CallbackTypes, CallbackQueryHandlers> callbackQueryHandlers;

    @Override
    public Optional<ResponseDto> handle(UpdateDto updateDto) {
        Integer updateId = updateDto.getUpdateId();
        log.debug("Выбор обработчика для события с id: {}", updateId);

        Optional<ResponseDto> response = Optional.empty();

        boolean messageIsPresent = Objects.nonNull(updateDto.getMessage());
        if (messageIsPresent) {
            List<MessageEntityDto> messageEntities = getMessageEntities(updateDto);
            List<MessageEntityDto> messageEntitiesWithCommand = getMessageEntitiesWithCommand(messageEntities);
            int messageEntitiesSize = messageEntitiesWithCommand.size();

            if (messageEntitiesSize == 0) {
                log.error("В сообщение нет команд. id: {}", updateId);
                return defaultResponseService.createResponse(updateDto);
            } else if (messageEntitiesSize > 1) {
                log.error("В сообщение больше одной команды. id: {}", updateId);
                return defaultResponseService.createResponse(updateDto);
            }

            MessageEntityDto messageEntityDto = messageEntitiesWithCommand.stream().findFirst()
                    .orElseThrow(() -> new RuntimeException("В сообщении нет ни одной команды. id: " + updateId));

            String command = messageEntityDto.getText();
            BotCommands botCommands = BotCommands.getBotCommands(command);

            response = Optional.ofNullable(botCommandHandlers.get(botCommands))
                    .flatMap(handler -> handler.handle(updateDto));
        }

        CallbackQueryDto callbackQuery = updateDto.getCallbackQuery();
        boolean callbackQueryIsPresent = Objects.nonNull(callbackQuery);

        if (callbackQueryIsPresent) {
            List<List<InlineKeyboardButtonDto>> keyboardButtonsList = getKeyboardButtonsList(callbackQuery);
            List<InlineKeyboardButtonDto> keyboardButtons = getKeyboardButtons(keyboardButtonsList);

            int keyboardButtonsSize = keyboardButtons.size();

            if (keyboardButtonsSize == 0) {
                log.error("В сообщение нет ответа на запрос. id: {}", updateId);
                return defaultResponseService.createResponse(updateDto);
            } else if (keyboardButtonsSize > 1) {
                log.error("В сообщение больше одного ответа на запрос. id: {}", updateId);
                return defaultResponseService.createResponse(updateDto);
            }

            InlineKeyboardButtonDto keyboardButtonDto = keyboardButtons.stream()
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("В сообщение нет ответа на запрос. id: " + updateId));

            CallbackTypes callbackType = CallbackTypes.getCallbackType(keyboardButtonDto.getCallbackData());

            response = Optional.ofNullable(callbackQueryHandlers.get(callbackType))
                    .flatMap(handler -> handler.handle(updateDto));
        }

        if (response.isEmpty()) {
            return defaultResponseService.createResponse(updateDto);
        }

        return response;
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
