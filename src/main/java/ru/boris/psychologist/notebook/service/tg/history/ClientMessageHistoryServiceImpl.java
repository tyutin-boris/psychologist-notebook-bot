package ru.boris.psychologist.notebook.service.tg.history;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.boris.psychologist.notebook.api.mapper.history.ClientMessageHistoryService;
import ru.boris.psychologist.notebook.api.repository.ClientMessageStepRepository;
import ru.boris.psychologist.notebook.entity.step.ClientMessageStepEntity;
import ru.boris.psychologist.notebook.dto.tg.step.ClientMessageStepType;

import java.util.Optional;

/**
 * Реализация сервиса для.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ClientMessageHistoryServiceImpl implements ClientMessageHistoryService {

    private final ClientMessageStepRepository clientMessageStepRepository;

    @Override
    public void saveRequestForAppointment(Long clientId, Integer updateId) {
        if (clientId == null) {
            log.error("Не удалось сохранить запрос записи на прием," +
                    " у пользователя нет идентификатора. updateId: {}", updateId);
            return;
        }

        ClientMessageStepEntity entity = new ClientMessageStepEntity();
        entity.setClientId(clientId);
        entity.setStep(ClientMessageStepType.REQUEST_FOR_APPOINTMENT);

        clientMessageStepRepository.save(entity);
        log.debug("Запрос записи на прием сохранен," +
                " для пользователя: {},  updateId: {}", clientId, updateId);
    }


    @Override
    public void saveAddPhoneNumberHistory(Long clientId, Integer updateId) {
        if (clientId == null) {
            log.error("Не удалось сохранить запись о запросе на добавление телефона," +
                    " у пользователя нет идентификатора. updateId: {}", updateId);
            return;
        }

        ClientMessageStepEntity entity = new ClientMessageStepEntity();
        entity.setClientId(clientId);
        entity.setStep(ClientMessageStepType.ADD_PHONE_NUMBER);

        clientMessageStepRepository.save(entity);
        log.debug("Сохранена запись о запросе на добавление телефона," +
                " для пользователя: {},  updateId: {}", clientId, updateId);
    }

    @Override
    public Optional<ClientMessageStepEntity> findLastMessageByClientId(Long clientId) {
        return clientMessageStepRepository.findFirstByClientIdOrderByCreateDateTimeDesc(clientId);
    }

    @Override
    public void saveAddedPhoneNumberHistory(Long clientId, Integer updateId) {
        if (clientId == null) {
            log.error("Не удалось сохранить запись о добавлении телефона," +
                    " у пользователя нет идентификатора. updateId: {}", updateId);
            return;
        }

        ClientMessageStepEntity entity = new ClientMessageStepEntity();
        entity.setClientId(clientId);
        entity.setStep(ClientMessageStepType.ADDED_PHONE_NUMBER);

        clientMessageStepRepository.save(entity);
        log.debug("Сохранена запись о добавлении телефона, для пользователя: {},  updateId: {}", clientId, updateId);
    }

    @Override
    public void saveAddDescriptionHistory(Long clientId, Integer updateId) {
        if (clientId == null) {
            log.error("Не удалось сохранить запись об описании проблемы, у пользователя нет идентификатора. " +
                    "updateId: {}", updateId);
            return;
        }

        ClientMessageStepEntity entity = new ClientMessageStepEntity();
        entity.setClientId(clientId);
        entity.setStep(ClientMessageStepType.ADD_DESCRIPTION);

        clientMessageStepRepository.save(entity);
        log.debug("Сохранена запись об описании проблемы, для пользователя: {},  updateId: {}", clientId, updateId);
    }

    @Override
    public void saveAddedDescriptionHistory(Long clientId, Integer updateId) {
        if (clientId == null) {
            log.error("Не удалось сохранить запись об добавлении описания, у пользователя нет идентификатора. " +
                    "updateId: {}", updateId);
            return;
        }

        ClientMessageStepEntity entity = new ClientMessageStepEntity();
        entity.setClientId(clientId);
        entity.setStep(ClientMessageStepType.ADDED_DESCRIPTION);

        clientMessageStepRepository.save(entity);
        log.debug("Сохранена запись об добавлении описания, для пользователя: {},  updateId: {}", clientId, updateId);
    }
}
