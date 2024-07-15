package ru.boris.psychologist.notebook.service.tg.command;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.boris.psychologist.notebook.api.mapper.bot.UserDtoToPatientDtoMapper;
import ru.boris.psychologist.notebook.api.service.bot.PatientService;
import ru.boris.psychologist.notebook.api.service.tg.command.BotCommandHandler;
import ru.boris.psychologist.notebook.dto.tg.ChatDto;
import ru.boris.psychologist.notebook.dto.tg.MessageDto;
import ru.boris.psychologist.notebook.dto.tg.ReplyKeyboardDto;
import ru.boris.psychologist.notebook.dto.tg.ResponseDto;
import ru.boris.psychologist.notebook.dto.tg.UpdateDto;
import ru.boris.psychologist.notebook.dto.tg.command.BotCommands;

import java.util.List;
import java.util.Optional;

/**
 * Реализация сервиса для обработки команды /start.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class StartBotCommandHandler implements BotCommandHandler {

    private final PatientService patientService;

    private final UserDtoToPatientDtoMapper userDtoToPatientDtoMapper;

    @Override
    @Transactional
    public Optional<ResponseDto> handle(UpdateDto dto) {
        Integer updateId = dto.getUpdateId();
        log.debug("Обработчик команды /start, начал обрабатывать событие с id: " + updateId);

        Optional<MessageDto> message = Optional.ofNullable(dto.getMessage());

        Long chatId = message
                .map(MessageDto::getChat)
                .map(ChatDto::getId)
                .orElseThrow(() -> new RuntimeException(
                        String.format("Не удалось определить идентификатор чата. messageId: %s", updateId)));

        ReplyKeyboardDto addPhoneNumber = getKeyboardForAddPhoneNumber();
        ReplyKeyboardDto addDescription = getKeyboardForAddDescription();

        ResponseDto response = new ResponseDto();
        response.setChatId(chatId);
        response.setText("Привет! Я бот.");
        response.setReplyMarkup(List.of(addPhoneNumber, addDescription));

        message.map(MessageDto::getFrom)
                .map(userDtoToPatientDtoMapper::toDto)
                .ifPresent(patientService::saveIfNotExist);

        log.debug("Обработчик команды /start, закончил обрабатывать событие с id: " + updateId);

        return Optional.of(response);
    }

    @Override
    public BotCommands getCommand() {
        return BotCommands.START;
    }

    private ReplyKeyboardDto getKeyboardForAddPhoneNumber() {
        ReplyKeyboardDto replyMarkup = new ReplyKeyboardDto();
        replyMarkup.setText("Добавить номер телефона.");
        replyMarkup.setCallbackData("add_phone_number");
        return replyMarkup;
    }

    private ReplyKeyboardDto getKeyboardForAddDescription() {
        ReplyKeyboardDto replyMarkup = new ReplyKeyboardDto();
        replyMarkup.setText("Описать проблему.");
        replyMarkup.setCallbackData("add_description");
        return replyMarkup;
    }
}
