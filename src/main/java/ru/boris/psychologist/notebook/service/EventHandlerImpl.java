package ru.boris.psychologist.notebook.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.boris.psychologist.notebook.dto.EventDto;
import ru.boris.psychologist.notebook.dto.ResponseDto;
import ru.boris.psychologist.notebook.exception.MessageHandlerException;
import ru.boris.psychologist.notebook.service.api.EventHandler;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventHandlerImpl implements EventHandler {


//    public SendMessage handle(Update update) {
//        Message message = getMessage(update);
//        Long chatId = getChatId(message);
//        log.debug("Получено сообщение из чата chatId: {}", chatId);
//
//        SendMessage sendMessage = new SendMessage();
//
//        sendMessage.setText("Hello");
//        sendMessage.setChatId(chatId);
//
//        return sendMessage;
//    }
//
//    private Message getMessage(Update update) {
//        return Optional.ofNullable(update)
//                .map(Update::getMessage)
//                .orElseThrow(() -> new MessageHandlerException("Отсутствует message"));
//    }
//
//    private Long getChatId(Message message) {
//        return Optional.ofNullable(message)
//                .map(Message::getChat)
//                .map(Chat::getId)
//                .orElseThrow(() -> new MessageHandlerException("Отсутствует chatId"));
//    }

    @Override
    public Optional<ResponseDto> handle(EventDto eventDto) {



//        Message message = getMessage(eventDto);
//                Long chatId = getChatId(message);
        return Optional.empty();
    }

    private Long getChatId(Message message) {
        return null;
    }

//    private Message getMessage(EventDto eventDto) {
//        return Optional.ofNullable(eventDto)
//                .map(Update::getMessage)
//                .orElseThrow(() -> new MessageHandlerException("Отсутствует message"));
//    }
}
