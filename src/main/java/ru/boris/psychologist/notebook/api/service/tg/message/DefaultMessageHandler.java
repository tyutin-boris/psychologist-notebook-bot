package ru.boris.psychologist.notebook.api.service.tg.message;

import ru.boris.psychologist.notebook.dto.tg.ResponseDto;
import ru.boris.psychologist.notebook.dto.tg.UpdateDto;

import java.util.Optional;

public interface DefaultMessageHandler {

    Optional<ResponseDto> handle(UpdateDto dto);
}
