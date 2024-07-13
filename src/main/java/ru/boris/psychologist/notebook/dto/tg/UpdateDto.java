package ru.boris.psychologist.notebook.dto.tg;

import lombok.Data;

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
