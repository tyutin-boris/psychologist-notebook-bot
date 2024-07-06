package ru.boris.psychologist.notebook.dto;

import lombok.Data;

/**
 * Сообщение.
 */
@Data
public class MessageDto {

    /**
     * Идентификатор сообщения.
     */
    private Integer messageId;

    /**
     * От кого пришло сообщение.
     */
    private UserDto from;


    /**
     * Чат из которого пришло сообщение.
     */
    private ChatDto chat;
}
