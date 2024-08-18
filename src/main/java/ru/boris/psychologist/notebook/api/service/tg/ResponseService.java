package ru.boris.psychologist.notebook.api.service.tg;

import ru.boris.psychologist.notebook.dto.tg.ResponseDto;
import ru.boris.psychologist.notebook.dto.tg.UpdateDto;

import java.util.Optional;

public interface ResponseService {

    Optional<ResponseDto> getStartCommandResponse(UpdateDto dto);

    Optional<ResponseDto> getMyTgIdCommandResponse(UpdateDto dto);

    Optional<ResponseDto> getNotDefinedComandResponse(UpdateDto dto);
}
