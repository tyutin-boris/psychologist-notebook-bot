package ru.boris.psychologist.notebook.dto.domain.callback;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

;

@Slf4j
@Getter
@RequiredArgsConstructor
public enum CallbackTypes {

    MAKE_AN_APPOINTMENT("Записаться на приём.", "make_an_appointment"),
    FROM_NINE_TO_TWELVE(PossibleCallTime.FROM_NINE_TO_TWELVE.getTime(), PossibleCallTime.FROM_NINE_TO_TWELVE.getName()),
    FROM_TWELVE_TO_THREE(PossibleCallTime.FROM_TWELVE_TO_THREE.getTime(), PossibleCallTime.FROM_TWELVE_TO_THREE.getName()),
    FROM_THREE_TO_SIX(PossibleCallTime.FROM_THREE_TO_SIX.getTime(), PossibleCallTime.FROM_THREE_TO_SIX.getName()),
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
            case "from_nine_to_twelve" -> FROM_NINE_TO_TWELVE;
            case "from_twelve_to_three" -> FROM_TWELVE_TO_THREE;
            case "from_three_to_six" -> FROM_THREE_TO_SIX;
            default -> NOT_DEFINED_CALLBACK_TYPE;
        };
    }
}
