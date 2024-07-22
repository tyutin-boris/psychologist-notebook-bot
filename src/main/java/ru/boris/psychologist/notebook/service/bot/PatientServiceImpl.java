package ru.boris.psychologist.notebook.service.bot;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.boris.psychologist.notebook.api.mapper.bot.PatientMapper;
import ru.boris.psychologist.notebook.api.repository.PatientRepository;
import ru.boris.psychologist.notebook.api.service.bot.PatientService;
import ru.boris.psychologist.notebook.dto.bot.PatientDto;
import ru.boris.psychologist.notebook.model.entity.PatientEntity;

import java.time.OffsetDateTime;

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

        Long tgId = dto.getTgId();
        String username = dto.getUsername();

        if (username == null) {
            log.error("Не удалось сохранить пациента, нет username");
            return;
        }

        boolean userExist = patientRepository.existsByUsernameOrTgId(username, tgId);

        if (userExist) {
            log.debug("Запись о пациенте с username: {} уже есть", username);
            return;
        }

        log.debug("Сохраняем пациента. username: {}, tgId: {}", username, tgId);
        PatientEntity entity = patientMapper.toEntity(dto);
        PatientEntity savedEntity = patientRepository.save(entity);
        log.debug("Успешно сохранили пациента. username: {}, tgId: {}, id: {}", username, tgId, savedEntity.getId());
    }

    @Override
    @Transactional
    public boolean savePhoneNumber(String phoneNumber, PatientDto dto) {
        if (dto == null) {
            log.error("Не удалось сохранить номер пациента пациента");
            return false;
        }

        Long tgId = dto.getTgId();
        String username = dto.getUsername();

        if (username == null) {
            log.error("Не удалось сохранить номер пациента, нет username");
            return false;
        }

        patientRepository.updatePhoneNumberByUsernameAndTgId(phoneNumber, OffsetDateTime.now(), username, tgId);

        log.debug("Успешно обновили номер телефона. username: {}, tgId: {}", username, tgId);
        return true;
    }

    @Override
    @Transactional
    public boolean saveDescription(String description, PatientDto dto) {
        if (dto == null) {
            log.error("Не удалось сохранить описание проблемы");
            return false;
        }

        Long tgId = dto.getTgId();
        String username = dto.getUsername();

        if (username == null) {
            log.error("Не удалось сохранить описание проблемы, нет username");
            return false;
        }

        patientRepository.updateDescriptionByUsernameAndTgId(description, OffsetDateTime.now(), username, tgId);

        log.debug("Успешно обновили описание проблемы. username: {}, tgId: {}", username, tgId);
        return true;
    }
}
