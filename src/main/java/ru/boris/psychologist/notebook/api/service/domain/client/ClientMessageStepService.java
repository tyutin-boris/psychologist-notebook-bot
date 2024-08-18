package ru.boris.psychologist.notebook.api.service.domain.client;

import ru.boris.psychologist.notebook.dto.domain.step.ClientMessageStepType;

/**
 * Сервис для работы с .
 */
public interface ClientMessageStepService {

    ClientMessageStepType findLastMessageByClientId(Long clientId);
}
