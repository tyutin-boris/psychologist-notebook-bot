package ru.boris.psychologist.notebook.api.service.tg.message;

import ru.boris.psychologist.notebook.dto.domain.step.ClientMessageStepType;
import ru.boris.psychologist.notebook.dto.tg.ResponseDto;
import ru.boris.psychologist.notebook.dto.tg.UpdateDto;

import java.util.Optional;

public interface MessageHandler {

    Optional<ResponseDto> handle(Long clientId, UpdateDto dto);

    ClientMessageStepType getStepType();
}
