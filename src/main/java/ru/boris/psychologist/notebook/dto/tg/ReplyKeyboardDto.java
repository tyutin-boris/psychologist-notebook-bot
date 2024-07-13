package ru.boris.psychologist.notebook.dto.tg;

import lombok.Data;

/**
 * Кнопки прикрепляемые к сообщению.
 */
@Data
public class ReplyKeyboardDto {

    /**
     * Название кнопки.
     */
    private String text;

    /**
     * Данные передаваемые ботом при нажатии кнопки.
     */
    private String callbackData;
}
