package ru.boris.psychologist.notebook.dto;

import lombok.Data;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

/**
 * Класс ответа в телеграм.
 */
@Data
public final class ResponseDto {

    /**
     * Сообщение.
     */
    private String text;

    /**
     * Идентификатор чата.
     */
    private Long chatId;

    /**
     * Кнопки прикрепляемые к сообщению.
     */
    private ReplyKeyboardDto replyMarkup;
}
