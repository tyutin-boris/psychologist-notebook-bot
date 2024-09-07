package ru.boris.psychologist.notebook.api.service.domain.appoitment;

import ru.boris.psychologist.notebook.dto.domain.callback.PossibleCallTime;
import ru.boris.psychologist.notebook.dto.tg.ResponseDto;
import ru.boris.psychologist.notebook.dto.tg.UpdateDto;

import java.util.Optional;

public interface PossibleCallTimeService {

    Optional<ResponseDto> handle(UpdateDto dto, PossibleCallTime possibleCallTime);
}
