package ru.boris.psychologist.notebook.api.service.tg.keyboard;

import ru.boris.psychologist.notebook.dto.tg.ReplyKeyboardDto;

import java.util.List;

/**
 * Сервис для создания кнопок у сообщения.
 */
public interface ReplyKeyboardService {

    ReplyKeyboardDto getKeyboardForMakeAnAppointment();

    List<ReplyKeyboardDto> getKeyboardForPossibleCallTime();
}
