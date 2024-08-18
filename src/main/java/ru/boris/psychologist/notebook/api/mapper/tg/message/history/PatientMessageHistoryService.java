package ru.boris.psychologist.notebook.api.mapper.tg.message.history;

import ru.boris.psychologist.notebook.model.entity.PatientMessageHistoryEntity;

import java.util.Optional;

public interface PatientMessageHistoryService {

    void saveRequestForAppointment(Long patientId, Integer updateId);

    void saveAddPhoneNumberHistory(Long patientId, Integer updateId);

    Optional<PatientMessageHistoryEntity> findLastMessageByPatientId(Long patientId);

    void saveAddedPhoneNumberHistory(Long patientId, Integer updateId);

    void saveAddDescriptionHistory(Long patientId, Integer updateId);

    void saveAddedDescriptionHistory(Long patientId, Integer updateId);
}
