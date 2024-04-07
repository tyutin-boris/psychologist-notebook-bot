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
import ru.boris.psychologist.notebook.bot.service.MessageService;
import ru.boris.psychologist.notebook.config.BotConfig;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TelegramBot extends TelegramLongPollingBot {

    private final BotConfig botConfig;

    private final MessageService messageService;

    @Override
    public void onUpdateReceived(Update update) {
        log.debug("Получено событие: {}", update);

        Message message = getMessage(update);
        log.debug("Получено сообщение: {}", message);

        SendMessage newMessage = messageService.getMessage(update);
        log.debug("Создан ответ: {}", newMessage);

        send(newMessage);
    }

    private Message getMessage(Update update) {
        return Optional.of(update)
                .map(Update::getMessage)
                .orElseThrow(() -> new SendMessageException(
                        String.format("Не удалось получить message, для сообщения: %s", update)));
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
            throw new SendMessageException(
                    String.format("Не удалось отправить ответ. chatId: %s, text: %s",
                            message.getChatId(),
                            message.getText()), e);
        }
    }

    private Long getChatId(Message message) {
        return Optional.ofNullable(message)
                .map(Message::getChatId)
                .orElseThrow(() -> new SendMessageException(
                        String.format("Не удалось получить catId, для сообщения: %s", message)));
    }
}
