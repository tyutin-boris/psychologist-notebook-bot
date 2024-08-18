package ru.boris.psychologist.notebook.dto.domain.step;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ClientMessageStepType {

    MAKE_AN_APPOINTMENT_ADD_NAME,

    MAKE_AN_APPOINTMENT_ADD_PHONE,

    NOT_DEFINED_MESSAGE_STEP;

}
