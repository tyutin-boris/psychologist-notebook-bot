package ru.boris.psychologist.notebook.service.bot;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.boris.psychologist.notebook.api.mapper.bot.PatientMapper;
import ru.boris.psychologist.notebook.api.repository.PatientRepository;
import ru.boris.psychologist.notebook.api.service.bot.PatientService;
import ru.boris.psychologist.notebook.dto.bot.PatientDto;
import ru.boris.psychologist.notebook.model.entity.PatientEntity;

/**
 * Реализация сервиса для пациента.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {


    private final PatientMapper patientMapper;

    private final PatientRepository patientRepository;

    @Override
    public void saveIfNotExist(PatientDto dto) {
        if (dto == null) {
            log.error("Не удалось сохранить пациента");
            return;
        }

        String username = dto.getUsername();

        if (username == null) {
            log.error("Не удалось сохранить пациента, нет username");
            return;
        }

        boolean userExist = patientRepository.existsByUsername(username);

        if (userExist) {
            log.debug("Запись о пациенте с username: {} уже есть", username);
            return;
        }

        PatientEntity entity = patientMapper.toEntity(dto);
        PatientEntity savedEntity = patientRepository.save(entity);
        log.debug("Успешно сохранили пациента. username: {}, id: {}", username, savedEntity.getId());
    }
}
