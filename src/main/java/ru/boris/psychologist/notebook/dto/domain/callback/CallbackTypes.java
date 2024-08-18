package ru.boris.psychologist.notebook.dto.domain.callback;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@RequiredArgsConstructor
public enum CallbackTypes {

    MAKE_AN_APPOINTMENT("Записаться на приём.", "make_an_appointment"),
    NOT_DEFINED_CALLBACK_TYPE("Тип callback не определён", "not_defined_callback_type");

    private final String text;
    private final String callbackData;

    public static CallbackTypes getCallbackType(String callbackData) {
        if (callbackData == null) {
            log.error("Не удалось определить тип ответного запроса: {}", callbackData);
            return NOT_DEFINED_CALLBACK_TYPE;
        }

        return switch (callbackData) {
            case "make_an_appointment" -> MAKE_AN_APPOINTMENT;
            default -> NOT_DEFINED_CALLBACK_TYPE;
        };
    }
}
