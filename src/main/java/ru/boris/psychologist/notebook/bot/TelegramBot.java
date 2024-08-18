package ru.boris.psychologist.notebook.bot;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.boris.psychologist.notebook.api.mapper.tg.SendMessageMapper;
import ru.boris.psychologist.notebook.api.mapper.tg.UpdateDtoMapper;
import ru.boris.psychologist.notebook.api.service.bot.UpdateHistoryService;
import ru.boris.psychologist.notebook.api.service.tg.UpdateHandler;
import ru.boris.psychologist.notebook.config.BotConfig;
import ru.boris.psychologist.notebook.exception.SendMessageException;

import java.util.List;
import java.util.Optional;

/**
 * Реализация для обработки и отправки сообщений в телеграмм.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TelegramBot extends TelegramLongPollingBot {

    private final BotConfig botConfig;

    private final UpdateHandler updateHandler;

    private final List<BotCommand> botCommands;

    private final UpdateDtoMapper updateDtoMapper;

    private final SendMessageMapper sendMessageMapper;

    private final UpdateHistoryService updateHistoryService;

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

    @Override
    public void onUpdateReceived(Update update) {
        log.debug("Получено событие: {}", update);
        updateHistoryService.saveUpdateHistory(update);

        Optional.ofNullable(update)
                .map(updateDtoMapper::toDto)
                .flatMap(updateHandler::handle)
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
