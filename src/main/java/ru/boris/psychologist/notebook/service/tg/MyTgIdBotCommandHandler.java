package ru.boris.psychologist.notebook.service.tg;

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
 * Реализация сервиса для обработки команды /tg_id.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MyTgIdBotCommandHandler implements BotCommandHandler {

    private final ResponseService responseService;

    @Override
    public Optional<ResponseDto> handle(UpdateDto dto) {
        Integer updateId = dto.getUpdateId();
        log.debug("Обработчик команды {}, начал обрабатывать событие с id: {}", BotCommands.MY_TG_ID, updateId);

        Optional<ResponseDto> userIdResponse = responseService.getMyTgIdCommandResponse(dto);

        log.debug("Конец обработки команды {}, событие с id: {}", BotCommands.MY_TG_ID, updateId);
        return userIdResponse;
    }

    @Override
    public BotCommands getCommand() {
        return BotCommands.MY_TG_ID;
    }
}
