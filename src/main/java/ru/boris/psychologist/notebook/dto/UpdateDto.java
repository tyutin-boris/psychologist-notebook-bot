package ru.boris.psychologist.notebook.dto;

import lombok.Data;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

import java.util.List;

/**
 * Событие полученное из, телеграмма.
 */
@Data
public class UpdateDto {

    /**
     * Идентификатор записи.
     */
    private Integer updateId;

    /**
     * Сообщение.
     */
    private MessageDto message;

    /**
     * .
     */
    private CallbackQueryDto callbackQuery;

}
