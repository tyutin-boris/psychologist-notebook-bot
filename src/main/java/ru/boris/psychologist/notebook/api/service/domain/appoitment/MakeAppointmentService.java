package ru.boris.psychologist.notebook.api.service.domain.appoitment;

import ru.boris.psychologist.notebook.dto.tg.ResponseDto;
import ru.boris.psychologist.notebook.dto.tg.UpdateDto;

import java.util.Optional;

public interface MakeAppointmentService {
    void saveNameForAppointment(UpdateDto dto);

    void savePoneNumberForAppointment(UpdateDto dto);
}
