package ru.boris.psychologist.notebook.service.tg.command;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.boris.psychologist.notebook.api.service.tg.ResponseService;
import ru.boris.psychologist.notebook.api.service.tg.command.BotCommandHandler;
import ru.boris.psychologist.notebook.dto.tg.ResponseDto;
import ru.boris.psychologist.notebook.dto.tg.UpdateDto;
import ru.boris.psychologist.notebook.dto.tg.command.BotCommands;

import java.util.Optional;

/**
 * Реализация сервиса для обработки команды /not_defined.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class NotDefinedBotCommandHandler implements BotCommandHandler {

    private final ResponseService responseService;

    @Override
    public Optional<ResponseDto> handle(UpdateDto dto) {
        Integer updateId = dto.getUpdateId();
        log.debug("Обработчик команды {}, начал обрабатывать событие с id: {}",
                BotCommands.COMMAND_NOT_DEFINED, updateId);

        Optional<ResponseDto> response = responseService.getNotDefinedComandResponse(dto);

        log.debug("Обработчик команды {}, закончил обрабатывать событие с id: {}",
                BotCommands.COMMAND_NOT_DEFINED, updateId);

        return response;
    }

    @Override
    public BotCommands getCommand() {
        return BotCommands.COMMAND_NOT_DEFINED;
    }
}
