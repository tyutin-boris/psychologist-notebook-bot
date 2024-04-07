package ru.boris.psychologist.notebook.bot;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.boris.psychologist.notebook.bot.exception.SendMessageException;
import ru.boris.psychologist.notebook.bot.service.api.UpdateHundler;
import ru.boris.psychologist.notebook.config.BotConfig;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TelegramBot extends TelegramLongPollingBot {

    private final BotConfig botConfig;

    private final UpdateHundler updateHundler;

    @Override
    public void onUpdateReceived(Update update) {
        log.debug("Получено событие: {}", update);

        Message message = getMessage(update);
        log.debug("Получено сообщение: {}", message);

        SendMessage newMessage = updateHundler.handle(update);
        log.debug("Создан ответ: {}", newMessage);

        send(newMessage);
    }

    private Message getMessage(Update update) {
        return Optional.ofNullable(update)
                .map(Update::getMessage)
                .orElseThrow(() -> new SendMessageException("В сообщении отсутствует message"));
    }

    @Override
    public String getBotUsername() {
        return botConfig.getName();
    }

    @Override
    public String getBotToken() {
        return botConfig.getToken();
    }

    @Override
    public void onRegister() {
        log.info("Бот зарегистрирован");
        super.onRegister();
    }

    private void send(SendMessage message) {
        log.debug("Отправляем ответ: {}", message);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            throw new SendMessageException("Не удалось отправить ответ", e);
        }
    }
}
