package ru.boris.psychologist.notebook.bot;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.boris.psychologist.notebook.config.BotConfig;
import ru.boris.psychologist.notebook.dto.ResponseDto;
import ru.boris.psychologist.notebook.exception.SendMessageException;
import ru.boris.psychologist.notebook.mapper.EventDtoMapper;
import ru.boris.psychologist.notebook.mapper.SendMessageMapper;
import ru.boris.psychologist.notebook.service.api.EventHandler;
import ru.boris.psychologist.notebook.dto.EventDto;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TelegramBot extends TelegramLongPollingBot {

    private final BotConfig botConfig;
    private final EventHandler eventHandler;
    private final EventDtoMapper eventDtoMapper;
    private final SendMessageMapper sendMessageMapper;

    @Override
    public void onUpdateReceived(Update update) {
        log.debug("Получено событие: {}", update);

        EventDto eventDto = eventDtoMapper.toDto(update);
        Optional<ResponseDto> responseDto = eventHandler.handle(eventDto);

        responseDto.map(sendMessageMapper::toDto)
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
