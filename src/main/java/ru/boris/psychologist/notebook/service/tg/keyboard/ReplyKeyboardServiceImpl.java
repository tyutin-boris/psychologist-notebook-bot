package ru.boris.psychologist.notebook.service.tg.keyboard;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.boris.psychologist.notebook.dto.tg.ReplyKeyboardDto;

/**
 * Реализация сервиса для создания кнопок у сообщения.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ReplyKeyboardServiceImpl implements ReplyKeyboardService {

    @Override
    public ReplyKeyboardDto getKeyboardForAddPhoneNumber() {
        ReplyKeyboardDto replyMarkup = new ReplyKeyboardDto();
        replyMarkup.setText("Добавить номер телефона.");
        replyMarkup.setCallbackData("add_phone_number");
        return replyMarkup;
    }

    @Override
    public ReplyKeyboardDto getKeyboardForAddDescription() {
        ReplyKeyboardDto replyMarkup = new ReplyKeyboardDto();
        replyMarkup.setText("Описать проблему.");
        replyMarkup.setCallbackData("add_description");
        return replyMarkup;
    }

    @Override
    public ReplyKeyboardDto getKeyboardForMakeAnAppointment() {
        ReplyKeyboardDto replyMarkup = new ReplyKeyboardDto();
        replyMarkup.setText("Записаться на приём");
        replyMarkup.setCallbackData("make_an_appointment");
        return replyMarkup;
    }
}
