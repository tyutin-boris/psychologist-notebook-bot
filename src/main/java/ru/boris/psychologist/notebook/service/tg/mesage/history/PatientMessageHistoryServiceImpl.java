package ru.boris.psychologist.notebook.service.tg.mesage.history;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.boris.psychologist.notebook.api.mapper.tg.message.history.PatientMessageHistoryService;
import ru.boris.psychologist.notebook.api.repository.PatientMessageHistoryRepository;
import ru.boris.psychologist.notebook.model.entity.PatientMessageHistoryEntity;
import ru.boris.psychologist.notebook.model.entity.PatientMessageHistoryType;

import java.util.Optional;

/**
 * Реализация сервиса для.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PatientMessageHistoryServiceImpl implements PatientMessageHistoryService {

    private final PatientMessageHistoryRepository patientMessageHistoryRepository;

    @Override
    public void saveAddPhoneNumberHistory(Long patientId, Integer updateId) {
        if (patientId == null) {
            log.error("Не удалось сохранить запись о запросе на добавление телефона," +
                    " у пользователя нет идентификатора. updateId: {}", updateId);
            return;
        }

        PatientMessageHistoryEntity entity = new PatientMessageHistoryEntity();
        entity.setPatientId(patientId);
        entity.setHistoryType(PatientMessageHistoryType.ADD_PHONE_NUMBER);

        patientMessageHistoryRepository.save(entity);
        log.debug("Сохранена запись о запросе на добавление телефона," +
                " для пользователя: {},  updateId: {}", patientId, updateId);
    }

    @Override
    public Optional<PatientMessageHistoryEntity> findLastMessageByPatientId(Long patientId) {
        return patientMessageHistoryRepository.findFirstByPatientIdOrderByCreateDateTimeDesc(patientId);
    }

    @Override
    public void saveAddedPhoneNumberHistory(Long patientId, Integer updateId) {
        if (patientId == null) {
            log.error("Не удалось сохранить запись о добавлении телефона," +
                    " у пользователя нет идентификатора. updateId: {}", updateId);
            return;
        }

        PatientMessageHistoryEntity entity = new PatientMessageHistoryEntity();
        entity.setPatientId(patientId);
        entity.setHistoryType(PatientMessageHistoryType.ADDED_PHONE_NUMBER);

        patientMessageHistoryRepository.save(entity);
        log.debug("Сохранена запись о добавлении телефона, для пользователя: {},  updateId: {}", patientId, updateId);
    }

    @Override
    public void saveAddDescriptionHistory(Long patientId, Integer updateId) {
        if (patientId == null) {
            log.error("Не удалось сохранить запись об описании проблемы, у пользователя нет идентификатора. " +
                    "updateId: {}", updateId);
            return;
        }

        PatientMessageHistoryEntity entity = new PatientMessageHistoryEntity();
        entity.setPatientId(patientId);
        entity.setHistoryType(PatientMessageHistoryType.ADD_DESCRIPTION);

        patientMessageHistoryRepository.save(entity);
        log.debug("Сохранена запись об описании проблемы, для пользователя: {},  updateId: {}", patientId, updateId);
    }

    @Override
    public void saveAddedDescriptionHistory(Long patientId, Integer updateId) {
        if (patientId == null) {
            log.error("Не удалось сохранить запись об добавлении описания, у пользователя нет идентификатора. " +
                    "updateId: {}", updateId);
            return;
        }

        PatientMessageHistoryEntity entity = new PatientMessageHistoryEntity();
        entity.setPatientId(patientId);
        entity.setHistoryType(PatientMessageHistoryType.ADDED_DESCRIPTION);

        patientMessageHistoryRepository.save(entity);
        log.debug("Сохранена запись об добавлении описания, для пользователя: {},  updateId: {}", patientId, updateId);
    }
}
