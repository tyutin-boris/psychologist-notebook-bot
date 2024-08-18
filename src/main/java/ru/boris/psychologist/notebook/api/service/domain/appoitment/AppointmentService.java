package ru.boris.psychologist.notebook.api.service.domain.appoitment;

public interface AppointmentService {

    void saveNameToContact(Long clientId, String nameToContact);

    void savePhoneNumber(Long clientId, String phoneNumber);
}
