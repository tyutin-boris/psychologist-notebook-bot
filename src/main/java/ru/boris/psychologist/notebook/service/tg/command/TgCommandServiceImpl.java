package ru.boris.psychologist.notebook.service.tg.command;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.boris.psychologist.notebook.api.service.tg.response.DefaultResponseService;
import ru.boris.psychologist.notebook.api.service.tg.command.TgBotCommandHandler;
import ru.boris.psychologist.notebook.api.service.tg.command.TgCommandService;
import ru.boris.psychologist.notebook.dto.tg.MessageDto;
import ru.boris.psychologist.notebook.dto.tg.MessageEntityDto;
import ru.boris.psychologist.notebook.dto.tg.ResponseDto;
import ru.boris.psychologist.notebook.dto.tg.UpdateDto;
import ru.boris.psychologist.notebook.dto.domain.command.BotCommands;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Реализация сервиса для работы с bot_command.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TgCommandServiceImpl implements TgCommandService {
    private final String botCommand = "bot_command";

    private final DefaultResponseService defaultResponseService;

    private final Map<BotCommands, TgBotCommandHandler> botCommandHandlers;

    @Override
    public Optional<ResponseDto> handlerCommand(UpdateDto updateDto,
                                                List<MessageEntityDto> messageEntitiesWithCommand) {
        Integer updateId = updateDto.getUpdateId();
        int commandSize = messageEntitiesWithCommand.size();

        if (commandSize > 1) {
            log.error("В сообщение больше одной команды. id: {}", updateId);
            return defaultResponseService.createResponse(updateDto);
        }

        MessageEntityDto messageEntityDto = getMessageEntityDto(messageEntitiesWithCommand, updateId);
        String command = messageEntityDto.getText();
        BotCommands botCommands = BotCommands.getBotCommands(command);

        return Optional.ofNullable(botCommandHandlers.get(botCommands))
                .flatMap(handler -> handler.handle(updateDto));
    }

    @Override
    public List<MessageEntityDto> getMessageEntitiesWithCommand(UpdateDto updateDto) {
        List<MessageEntityDto> messageEntities = Optional.ofNullable(updateDto.getMessage())
                .map(MessageDto::getEntities)
                .orElse(Collections.emptyList());

        return messageEntities.stream()
                .filter(dto -> botCommand.equals(dto.getType()))
                .toList();
    }

    private MessageEntityDto getMessageEntityDto(List<MessageEntityDto> messageEntitiesWithCommand, Integer updateId) {
        return messageEntitiesWithCommand.stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("В сообщении нет ни одной команды. id: " + updateId));
    }
}
