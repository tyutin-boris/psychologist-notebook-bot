package ru.boris.psychologist.notebook.service.domain.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.boris.psychologist.notebook.api.repository.ClientMessageStepRepository;
import ru.boris.psychologist.notebook.api.service.domain.client.ClientMessageStepService;
import ru.boris.psychologist.notebook.dto.domain.step.ClientMessageStepType;
import ru.boris.psychologist.notebook.entity.step.ClientMessageStepEntity;

/**
 * Реализация сервиса для.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ClientMessageStepServiceImpl implements ClientMessageStepService {

    private final ClientMessageStepRepository clientMessageStepRepository;

    @Override
    public ClientMessageStepType findLastMessageByClientId(Long clientId) {
        return clientMessageStepRepository.findFirstByClientIdOrderByCreateDateTimeDesc(clientId)
                .map(ClientMessageStepEntity::getNextStep)
                .orElse(ClientMessageStepType.NOT_DEFINED_MESSAGE_STEP);
    }
}
