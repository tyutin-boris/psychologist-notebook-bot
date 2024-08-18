package ru.boris.psychologist.notebook.api.mapper.history;

import ru.boris.psychologist.notebook.entity.step.ClientMessageStepEntity;

import java.util.Optional;

public interface ClientMessageHistoryService {

    void saveRequestForAppointment(Long clientId, Integer updateId);

    void saveAddPhoneNumberHistory(Long clientId, Integer updateId);

    Optional<ClientMessageStepEntity> findLastMessageByClientId(Long clientId);

    void saveAddedPhoneNumberHistory(Long clientId, Integer updateId);

    void saveAddDescriptionHistory(Long clientId, Integer updateId);

    void saveAddedDescriptionHistory(Long clientId, Integer updateId);
}
