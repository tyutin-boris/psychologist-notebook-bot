package ru.boris.psychologist.notebook.service.tg.command;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.boris.psychologist.notebook.api.mapper.bot.UserDtoToPatientDtoMapper;
import ru.boris.psychologist.notebook.api.service.bot.PatientService;
import ru.boris.psychologist.notebook.api.service.tg.StartResponseService;
import ru.boris.psychologist.notebook.api.service.tg.command.BotCommandHandler;
import ru.boris.psychologist.notebook.dto.tg.ChatDto;
import ru.boris.psychologist.notebook.dto.tg.MessageDto;
import ru.boris.psychologist.notebook.dto.tg.ResponseDto;
import ru.boris.psychologist.notebook.dto.tg.UpdateDto;
import ru.boris.psychologist.notebook.dto.tg.command.BotCommands;

import java.util.Optional;

/**
 * Реализация сервиса для обработки команды /start.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class StartBotCommandHandler implements BotCommandHandler {

    private final PatientService patientService;

    private final StartResponseService startResponseService;

    private final UserDtoToPatientDtoMapper userDtoToPatientDtoMapper;

    @Override
    @Transactional
    public Optional<ResponseDto> handle(UpdateDto dto) {
        Integer updateId = dto.getUpdateId();
        log.debug("Обработчик команды /start, начал обрабатывать событие с id: " + updateId);

        MessageDto message = dto.getMessage();
        Optional<MessageDto> messageOpt = Optional.ofNullable(message);

        Long chatId = messageOpt
                .map(MessageDto::getChat)
                .map(ChatDto::getId)
                .orElseThrow(() -> new RuntimeException(
                        String.format("Не удалось определить идентификатор чата. messageId: %s", updateId)));

        messageOpt.map(MessageDto::getFrom)
                .map(userDtoToPatientDtoMapper::toDto)
                .ifPresent(patientService::saveIfNotExist);

        log.debug("Обработчик команды /start, закончил обрабатывать событие с id: " + updateId);
        ResponseDto responseDto = startResponseService.getStartResponseDto(message, chatId);

        return Optional.of(responseDto);
    }

    @Override
    public BotCommands getCommand() {
        return BotCommands.START;
    }
}
