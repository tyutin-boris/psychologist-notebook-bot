package ru.boris.psychologist.notebook.api.service.tg.response;

import ru.boris.psychologist.notebook.dto.tg.ResponseDto;
import ru.boris.psychologist.notebook.dto.tg.UpdateDto;

import java.util.Optional;

public interface ResponseAppointmentService {

    Optional<ResponseDto> getNameToContactResponse(UpdateDto dto);

    Optional<ResponseDto> getPhoneNumberResponse(UpdateDto dto);

    Optional<ResponseDto> getPossibleCallTime(UpdateDto dto);

    Optional<ResponseDto> getEndResponse(UpdateDto dto);

    Optional<ResponseDto> getQquestionResponse(UpdateDto dto);
}
