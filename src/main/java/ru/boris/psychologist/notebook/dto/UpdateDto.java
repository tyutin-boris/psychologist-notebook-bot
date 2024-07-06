package ru.boris.psychologist.notebook.dto;

import lombok.Data;

/**
 * Событие полученное из телеграмма.
 */
@Data
public class UpdateDto {

    /**
     * Сообщение.
     */
    private MessageDto message;

}
