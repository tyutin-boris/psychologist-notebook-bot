package ru.boris.psychologist.notebook.service.command;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.boris.psychologist.notebook.api.service.command.BotCommandHandler;
import ru.boris.psychologist.notebook.dto.tg.ChatDto;
import ru.boris.psychologist.notebook.dto.tg.MessageDto;
import ru.boris.psychologist.notebook.dto.tg.ReplyKeyboardDto;
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

    @Override
    public Optional<ResponseDto> handle(UpdateDto dto) {
        Integer updateId = dto.getUpdateId();
        log.debug("Обработчик команды /start, начал обрабатывать событие с id: " + updateId);

        Long chatId = Optional.ofNullable(dto.getMessage())
                .map(MessageDto::getChat)
                .map(ChatDto::getId)
                .orElseThrow(() -> new RuntimeException(
                        String.format("Не удалось определить идентификатор чата. messageId: %s", updateId)));

        ReplyKeyboardDto replyMarkup = new ReplyKeyboardDto();
        replyMarkup.setText("Добавить номер телефона.");
        replyMarkup.setCallbackData("add_phone_number");

        ResponseDto response = new ResponseDto();
        response.setChatId(chatId);
        response.setText("Привет! Я бот.");
        response.setReplyMarkup(replyMarkup);

        log.debug("Обработчик команды /start, закончил обрабатывать событие с id: " + updateId);

        return Optional.of(response);
    }

    @Override
    public BotCommands getCommand() {
        return BotCommands.START;
    }
}
