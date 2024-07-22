package ru.boris.psychologist.notebook.dto.tg;

import lombok.Data;

import java.util.List;

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

    /**
     * .
     */
    private List<MessageEntityDto> entities;

    private InlineKeyboardMarkupDto replyMarkup;

    private String text;
}
