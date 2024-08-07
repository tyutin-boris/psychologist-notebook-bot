package ru.boris.psychologist.notebook.dto.tg;

import lombok.Data;

/**
 * Информация о чате.
 */
@Data
public class ChatDto {

    /**
     * Идентификатор чата.
     */
    private Long id;

    /**
     * Тип чата
     */
    private ChatType type;

    /**
     * Имя.
     */
    private String firstName;

    /**
     * Фамилия.
     */
    private String lastName;

    /**
     * Имя пользователя.
     */
    private String userName;
}
