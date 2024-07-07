package ru.boris.psychologist.notebook.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.boris.psychologist.notebook.api.service.DefaultResponseService;
import ru.boris.psychologist.notebook.api.service.UpdateHandler;
import ru.boris.psychologist.notebook.api.service.command.BotCommandHandler;
import ru.boris.psychologist.notebook.dto.MessageDto;
import ru.boris.psychologist.notebook.dto.MessageEntityDto;
import ru.boris.psychologist.notebook.dto.ResponseDto;
import ru.boris.psychologist.notebook.dto.UpdateDto;
import ru.boris.psychologist.notebook.dto.command.BotCommands;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UpdateHandlerImpl implements UpdateHandler {

    private final DefaultResponseService defaultResponseService;

    private final Map<BotCommands, BotCommandHandler> botCommandHandlers;

    @Override
    public Optional<ResponseDto> handle(UpdateDto updateDto) {
        Integer updateId = updateDto.getUpdateId();
        log.debug("Выбор обработчика для события с id: {}", updateId);

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

        Optional<ResponseDto> response = Optional.ofNullable(botCommandHandlers.get(botCommands))
                .flatMap(handler -> handler.handle(updateDto));

        if (response.isEmpty()) {
            return defaultResponseService.createResponse(updateDto);
        }

        return response;
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
