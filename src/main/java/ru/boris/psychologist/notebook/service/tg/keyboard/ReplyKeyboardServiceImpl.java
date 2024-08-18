package ru.boris.psychologist.notebook.service.tg.keyboard;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.boris.psychologist.notebook.api.service.tg.keyboard.ReplyKeyboardService;
import ru.boris.psychologist.notebook.dto.tg.ReplyKeyboardDto;

/**
 * Реализация сервиса для создания кнопок у сообщения.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ReplyKeyboardServiceImpl implements ReplyKeyboardService {

    @Override
    public ReplyKeyboardDto getKeyboardForMakeAnAppointment() {
        ReplyKeyboardDto replyMarkup = new ReplyKeyboardDto();
        replyMarkup.setText("Записаться на приём");
        replyMarkup.setCallbackData("make_an_appointment");
        return replyMarkup;
    }
}
