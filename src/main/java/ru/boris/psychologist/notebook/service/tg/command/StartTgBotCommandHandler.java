package ru.boris.psychologist.notebook.service.tg.command;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.boris.psychologist.notebook.api.mapper.client.UserDtoToClientDtoMapper;
import ru.boris.psychologist.notebook.api.service.domain.client.ClientService;
import ru.boris.psychologist.notebook.api.service.tg.response.ResponseCommandService;
import ru.boris.psychologist.notebook.api.service.tg.command.TgBotCommandHandler;
import ru.boris.psychologist.notebook.dto.tg.MessageDto;
import ru.boris.psychologist.notebook.dto.tg.ResponseDto;
import ru.boris.psychologist.notebook.dto.tg.UpdateDto;
import ru.boris.psychologist.notebook.dto.domain.command.BotCommands;

import java.util.Optional;

/**
 * Реализация сервиса для обработки команды /start.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class StartTgBotCommandHandler implements TgBotCommandHandler {

    private final ClientService clientService;

    private final ResponseCommandService responseCommandService;

    private final UserDtoToClientDtoMapper userDtoToClientDtoMapper;

    @Override
    @Transactional
    public Optional<ResponseDto> handle(UpdateDto dto) {
        Integer updateId = dto.getUpdateId();
        log.debug("Обработчик команды {}, начал обрабатывать событие с id: {}",
                BotCommands.START.getCommand(), updateId);

        Optional.ofNullable(dto.getMessage())
                .map(MessageDto::getFrom)
                .map(userDtoToClientDtoMapper::toDto)
                .ifPresent(clientService::saveIfNotExist);

        Optional<ResponseDto> startResponseDto = responseCommandService.getStartCommandResponse(dto);

        log.debug("Обработчик команды {}, закончил обрабатывать событие с id: {}",
                BotCommands.START.getCommand(), updateId);

        return startResponseDto;
    }

    @Override
    public BotCommands getCommand() {
        return BotCommands.START;
    }
}
