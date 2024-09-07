package ru.boris.psychologist.notebook.dto.domain.callback;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PossibleCallTime {

    FROM_NINE_TO_TWELVE("from_nine_to_twelve", "9:00-12:00"),
    FROM_TWELVE_TO_THREE("from_twelve_to_three", "12:00-15:00"),
    FROM_THREE_TO_SIX("from_three_to_six", "15:00-18:00");

    private final String name;

    private final String time;
}
