package ru.boris.psychologist.notebook.dto;

import lombok.Data;

/**
 * Класс ответа в телеграм.
 */
@Data
public final class ResponseDto {

    /**
     * Сообщение.
     */
    private String message;

    /**
     * Идентификатор чата.
     */
    private Long chatId;
}
