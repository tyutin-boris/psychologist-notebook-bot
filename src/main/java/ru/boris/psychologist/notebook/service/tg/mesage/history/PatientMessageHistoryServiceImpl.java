package ru.boris.psychologist.notebook.service.tg.mesage.history;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.boris.psychologist.notebook.api.mapper.tg.message.history.PatientMessageHistoryService;
import ru.boris.psychologist.notebook.api.repository.PatientMessageHistoryRepository;
import ru.boris.psychologist.notebook.model.entity.PatientMessageHistoryEntity;
import ru.boris.psychologist.notebook.model.entity.PatientMessageHistoryType;

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
            log.error("Не удалось сохранить запись об запросе на добавление телефона," +
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
}
