package ru.boris.psychologist.notebook.bot;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.boris.psychologist.notebook.config.BotConfig;
import ru.boris.psychologist.notebook.exception.SendMessageException;
import ru.boris.psychologist.notebook.mapper.EventDtoMapper;
import ru.boris.psychologist.notebook.mapper.SendMessageMapper;
import ru.boris.psychologist.notebook.service.api.EventHandler;

import java.util.List;
import java.util.Optional;

/**
 * Реализация для обработки и отправки сообщений в телеграмм.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TelegramBot extends TelegramLongPollingBot {

    private final ObjectMapper objectMapper;

    private final BotConfig botConfig;
    private final EventHandler eventHandler;
    private final EventDtoMapper eventDtoMapper;
    private final SendMessageMapper sendMessageMapper;

    private final List<BotCommand> botCommands;

    @PostConstruct
    private void setUp() {
        try {
            String languageCode = null;
            BotCommandScopeDefault scope = new BotCommandScopeDefault();
            SetMyCommands method = new SetMyCommands(botCommands, scope, languageCode);

            execute(method);
            log.info("Добавлены команды для бота: " + botCommands);
        } catch (TelegramApiException e) {
            log.error("Не удалось добавить команды", e);
        }
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        log.debug("Получено событие: {}", update);
        String json = objectMapper.writeValueAsString(update);

        Optional.ofNullable(update)
                .map(eventDtoMapper::toDto)
                .flatMap(eventHandler::handle)
                .map(sendMessageMapper::toDto)
                .ifPresent(this::send);

        log.debug("Закончена обработка события: {}", update);
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
