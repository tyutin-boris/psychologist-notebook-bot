package ru.boris.psychologist.notebook.dto.domain.step;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ClientMessageStepType {

    MAKE_AN_APPOINTMENT_ADD_NAME,

    MAKE_AN_APPOINTMENT_ADD_PHONE,

    MAKE_AN_APPOINTMENT_ADD_POSSIBLE_CALL_TIME,

    MAKE_AN_APPOINTMENT_ADD_QUESTION,

    MAKE_AN_APPOINTMENT_ADD_END,

    NOT_DEFINED_MESSAGE_STEP;

}
