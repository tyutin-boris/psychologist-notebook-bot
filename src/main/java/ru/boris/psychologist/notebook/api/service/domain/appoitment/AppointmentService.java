package ru.boris.psychologist.notebook.api.service.domain.appoitment;

import ru.boris.psychologist.notebook.dto.domain.callback.PossibleCallTime;

public interface AppointmentService {

    void saveNameToContact(Long clientId, String nameToContact);

    void savePhoneNumber(Long clientId, String phoneNumber);

    void saveQuestion(Long clientId, String question);

    void savePossibleCallTime(Long clientId, PossibleCallTime possibleCallTime);

    void saveAppointment(Long clientId);
}
