package ru.boris.psychologist.notebook.dto.tg;

import lombok.Data;

@Data
public class MessageEntityDto {

    /**
     * Тип сообщения.
     */
    private String type;

    /**
     * Текст сообщения.
     */
    private String text;
}
