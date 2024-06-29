package ru.boris.psychologist.notebook.dto;

import lombok.Data;

/**
 * Событие полученное из телеграмма.
 */
@Data
public class EventDto {

    /**
     * От кого пришло сообщение.
     */
    private FromDto from;


    /**
     * Чат из которого пришло сообщение.
     */
    private ChatDto chat;
}
