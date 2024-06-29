package ru.boris.psychologist.notebook.dto;

import lombok.Data;

/**
 * Информация об источнике сообщения.
 */
@Data
public class FromDto {

    /**
     * Идентификатор пользователя.
     */
    private String id;

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
    private String username;
}
