package ru.boris.psychologist.notebook.dto.bot;

import lombok.Data;

/**
 * Класс пациент.
 */
@Data
public class PatientDto {

    /**
     * Идентификатор телеграм пользователя.
     */
    private Long tgId;

    /**
     * Имя.
     */
    private String firstName;

    /**
     * Фамилия.
     */
    private String lastName;

    /**
     * Псевдоним.
     */
    private String username;

    /**
     * Номер телефона.
     */
    private String phoneNumber;

    /**
     * Описание проблемы.
     */
    private String description;
}
