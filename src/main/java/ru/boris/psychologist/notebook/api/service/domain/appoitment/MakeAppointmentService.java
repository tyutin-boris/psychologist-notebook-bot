package ru.boris.psychologist.notebook.api.service.domain.appoitment;

import ru.boris.psychologist.notebook.dto.domain.callback.PossibleCallTime;
import ru.boris.psychologist.notebook.dto.tg.UpdateDto;

public interface MakeAppointmentService {
    void saveNameForAppointment(UpdateDto dto);

    void savePoneNumberForAppointment(UpdateDto dto);

    void saveQuestionForAppointment(UpdateDto dto);

    void savePossibleCalltimeForAppointment(UpdateDto dto, PossibleCallTime possibleCallTime);
}
