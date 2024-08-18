package ru.boris.psychologist.notebook.service.tg.command;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.boris.psychologist.notebook.api.service.tg.response.ResponseCommandService;
import ru.boris.psychologist.notebook.api.service.tg.command.TgBotCommandHandler;
import ru.boris.psychologist.notebook.dto.tg.ResponseDto;
import ru.boris.psychologist.notebook.dto.tg.UpdateDto;
import ru.boris.psychologist.notebook.dto.domain.command.BotCommands;

import java.util.Optional;

/**
 * Реализация сервиса для обработки команды /not_defined.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class NotDefinedTgBotCommandHandler implements TgBotCommandHandler {

    private final ResponseCommandService responseCommandService;

    @Override
    public Optional<ResponseDto> handle(UpdateDto dto) {
        Integer updateId = dto.getUpdateId();
        log.debug("Обработчик команды {}, начал обрабатывать событие с id: {}",
                BotCommands.COMMAND_NOT_DEFINED, updateId);

        Optional<ResponseDto> response = responseCommandService.getNotDefinedCommandResponse(dto);

        log.debug("Обработчик команды {}, закончил обрабатывать событие с id: {}",
                BotCommands.COMMAND_NOT_DEFINED, updateId);

        return response;
    }

    @Override
    public BotCommands getCommand() {
        return BotCommands.COMMAND_NOT_DEFINED;
    }
}
