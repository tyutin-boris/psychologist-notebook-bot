package ru.boris.psychologist.notebook.bot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import ru.boris.psychologist.notebook.api.repository.UpdateHistoryRepository;
import ru.boris.psychologist.notebook.api.service.tg.UpdateHandler;
import ru.boris.psychologist.notebook.config.BotConfig;
import ru.boris.psychologist.notebook.exception.SendMessageException;
import ru.boris.psychologist.notebook.model.entity.UpdateHistoryEntity;

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
    private final UpdateHandler updateHandler;
    private final UpdateDtoMapper updateDtoMapper;
    private final SendMessageMapper sendMessageMapper;
    private final UpdateHistoryRepository updateHistoryRepository;

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

    @Override
    public void onUpdateReceived(Update update) {
        log.debug("Получено событие: {}", update);
        saveUpdateHistory(update);

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

    private void saveUpdateHistory(Update update) {
        String json = getString(update);
        UpdateHistoryEntity entity = new UpdateHistoryEntity();
        entity.setJson(json);
        updateHistoryRepository.save(entity);
    }

    private String getString(Update update) {
        try {
            return objectMapper.writeValueAsString(update);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
