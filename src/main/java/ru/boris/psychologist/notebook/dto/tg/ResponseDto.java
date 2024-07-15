package ru.boris.psychologist.notebook.dto.tg;

import lombok.Data;

import java.util.List;

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
    private List<ReplyKeyboardDto> replyMarkup;
}
