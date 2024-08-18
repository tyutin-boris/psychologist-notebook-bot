package ru.boris.psychologist.notebook.service.domain.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.boris.psychologist.notebook.api.mapper.client.ClientMapper;
import ru.boris.psychologist.notebook.api.repository.ClientRepository;
import ru.boris.psychologist.notebook.api.service.domain.client.ClientService;
import ru.boris.psychologist.notebook.dto.domain.ClientDto;
import ru.boris.psychologist.notebook.entity.ClientEntity;

import java.time.OffsetDateTime;

/**
 * Реализация сервиса для пациента.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientMapper clientMapper;

    private final ClientRepository clientRepository;

    @Override
    public void saveIfNotExist(ClientDto dto) {
        if (dto == null) {
            log.error("Не удалось сохранить информацию о клиенте dto is null");
            return;
        }

        Long tgId = dto.getTgId();
        String username = dto.getUsername();

        if (username == null) {
            log.error("Не удалось сохранить клиента, нет username");
            return;
        }

        boolean userExist = clientRepository.existsByUsernameOrTgId(username, tgId);

        if (userExist) {
            log.debug("Запись о клиенте с username: {} уже есть", username);
            return;
        }

        log.debug("Сохраняем клиент. username: {}, tgId: {}", username, tgId);
        ClientEntity entity = clientMapper.toEntity(dto);
        ClientEntity savedEntity = clientRepository.save(entity);
        log.debug("Успешно сохранили информацию о клиенте. username: {}, tgId: {}, id: {}",
                username, tgId, savedEntity.getId());
    }

    @Override
    @Transactional
    public boolean savePhoneNumber(String phoneNumber, ClientDto dto) {
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

//        clientRepository.updatePhoneNumberByUsernameAndTgId(phoneNumber, OffsetDateTime.now(), username, tgId);

        log.debug("Успешно обновили номер телефона. username: {}, tgId: {}", username, tgId);
        return true;
    }

    @Override
    @Transactional
    public boolean saveDescription(String description, ClientDto dto) {
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

//        clientRepository.updateDescriptionByUsernameAndTgId(description, OffsetDateTime.now(), username, tgId);

        log.debug("Успешно обновили описание проблемы. username: {}, tgId: {}", username, tgId);
        return true;
    }

    @Override
    public void saveNameToContact(Long id, String clientName) {

    }
}
