package ru.boris.psychologist.notebook.service.tg.keyboard;

import ru.boris.psychologist.notebook.dto.tg.ReplyKeyboardDto;

/**
 * Сервис для создания кнопок у сообщения.
 */
public interface ReplyKeyboardService {

    ReplyKeyboardDto getKeyboardForAddPhoneNumber();

    ReplyKeyboardDto getKeyboardForAddDescription();

    ReplyKeyboardDto getKeyboardForMakeAnAppointment();
}
