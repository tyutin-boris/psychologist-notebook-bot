package ru.boris.psychologist.notebook.dto;

import lombok.Data;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

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
}
