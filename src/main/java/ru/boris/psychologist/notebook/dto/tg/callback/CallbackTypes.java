package ru.boris.psychologist.notebook.dto.tg.callback;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@RequiredArgsConstructor
public enum CallbackTypes {

    ADD_PHONE_NUMBER("Добавить номер телефона.", "add_phone_number"),
    ADD_DESCRIPTION("Описать проблему.", "add_description"),
    MAKE_AN_APPOINTMENT("Записаться на приём.", "make_an_appointment"),
    NOT_DEFINED_CALLBACK_TYPE("", "not_defined_callback_type");

    private final String text;
    private final String callbackData;

    public static CallbackTypes getCallbackType(String callbackData) {
        if (callbackData == null) {
            log.error("Не удалось определить тип ответного запроса: {}", callbackData);
            return NOT_DEFINED_CALLBACK_TYPE;
        }

        return switch (callbackData) {
            case "add_phone_number" -> ADD_PHONE_NUMBER;
            case "add_description" -> ADD_DESCRIPTION;
            case "make_an_appointment" -> MAKE_AN_APPOINTMENT;
            default -> NOT_DEFINED_CALLBACK_TYPE;
        };
    }
}
