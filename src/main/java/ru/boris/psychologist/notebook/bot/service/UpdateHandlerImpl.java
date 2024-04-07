package ru.boris.psychologist.notebook.bot.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.boris.psychologist.notebook.bot.exception.MessageHandlerException;
import ru.boris.psychologist.notebook.bot.service.api.UpdateHundler;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UpdateHandlerImpl implements UpdateHundler {


    @Override
    public SendMessage handle(Update update) {
        Message message = getMessage(update);
        Long chatId = getChatId(message);
        log.debug("Получено сообщение из чата chatId: {}", chatId);

        SendMessage sendMessage = new SendMessage();

        sendMessage.setText("Hello");
        sendMessage.setChatId(chatId);

        return sendMessage;
    }

    private Message getMessage(Update update) {
        return Optional.ofNullable(update)
                .map(Update::getMessage)
                .orElseThrow(() -> new MessageHandlerException("Отсутствует message"));
    }

    private Long getChatId(Message message) {
        return Optional.ofNullable(message)
                .map(Message::getChat)
                .map(Chat::getId)
                .orElseThrow(() -> new MessageHandlerException("Отсутствует chatId"));
    }
}
