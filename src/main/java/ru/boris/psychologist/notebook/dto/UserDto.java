package ru.boris.psychologist.notebook.dto;

import lombok.Data;

/**
 * Информация об источнике сообщения.
 */
@Data
public class UserDto {

    /**
     * Идентификатор пользователя.
     */
    private Long id;

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
