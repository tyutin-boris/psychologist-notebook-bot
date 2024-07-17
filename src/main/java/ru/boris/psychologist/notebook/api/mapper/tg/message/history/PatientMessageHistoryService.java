package ru.boris.psychologist.notebook.api.mapper.tg.message.history;

public interface PatientMessageHistoryService {
    void saveAddPhoneNumberHistory(Long patientId, Integer updateId);
}
