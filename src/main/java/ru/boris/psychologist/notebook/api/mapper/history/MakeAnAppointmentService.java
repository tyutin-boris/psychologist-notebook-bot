package ru.boris.psychologist.notebook.api.mapper.history;

/**
 * Сервис для записи на прием.
 */
public interface MakeAnAppointmentService {

    void saveNextStepAppointmentAddName(Long clientId, Integer updateId);

    void saveNextStepAppointmentAddPhoneNumber(Long clientId, Integer updateId);

    void saveAppointmentAddPossibleCallTime(Long clientId, Integer updateId);

    void saveAppointmentAddQuestion(Long clientId, Integer updateId);

    void saveAppointmentAddEnd(Long clientId, Integer updateId);
}
