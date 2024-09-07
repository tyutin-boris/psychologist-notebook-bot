package ru.boris.psychologist.notebook.service.domain.apointment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.boris.psychologist.notebook.api.mapper.history.MakeAnAppointmentService;
import ru.boris.psychologist.notebook.api.repository.ClientMessageStepRepository;
import ru.boris.psychologist.notebook.dto.domain.step.ClientMessageStepType;
import ru.boris.psychologist.notebook.entity.step.ClientMessageStepEntity;

/**
 * Реализация сервиса для записи на прием.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MakeAnAppointmentServiceImpl implements MakeAnAppointmentService {

    private final ClientMessageStepRepository clientMessageStepRepository;

    @Override
    public void saveNextStepAppointmentAddName(Long clientId, Integer updateId) {
        ClientMessageStepEntity entity = new ClientMessageStepEntity();
        entity.setClientId(clientId);
        entity.setNextStep(ClientMessageStepType.MAKE_AN_APPOINTMENT_ADD_NAME);

        clientMessageStepRepository.save(entity);
        log.debug("Текущее состояние переписки добавление имени клиента: {}, updateId: {}", clientId, updateId);
    }

    @Override
    public void saveNextStepAppointmentAddPhoneNumber(Long clientId, Integer updateId) {
        ClientMessageStepEntity entity = new ClientMessageStepEntity();
        entity.setClientId(clientId);
        entity.setNextStep(ClientMessageStepType.MAKE_AN_APPOINTMENT_ADD_PHONE);

        clientMessageStepRepository.save(entity);
        log.debug("Текущее состояние переписки добавление номера клиента. updateId: {}, clientId: {}",
                clientId, updateId);
    }

    @Override
    public void saveAppointmentAddPossibleCallTime(Long clientId, Integer updateId) {
        ClientMessageStepEntity entity = new ClientMessageStepEntity();
        entity.setClientId(clientId);
        entity.setNextStep(ClientMessageStepType.MAKE_AN_APPOINTMENT_ADD_POSSIBLE_CALL_TIME);

        clientMessageStepRepository.save(entity);
        log.debug("Текущее состояние переписки добавление возможного времени для звонка. updateId: {}, clientId: {}",
                clientId, updateId);
    }

    @Override
    public void saveAppointmentAddQuestion(Long clientId, Integer updateId) {
        ClientMessageStepEntity entity = new ClientMessageStepEntity();
        entity.setClientId(clientId);
        entity.setNextStep(ClientMessageStepType.MAKE_AN_APPOINTMENT_ADD_QUESTION);

        clientMessageStepRepository.save(entity);
        log.debug("Текущее состояние переписки добавление вопроса для обращения. updateId: {}, clientId: {}",
                clientId, updateId);
    }

    @Override
    public void saveAppointmentAddEnd(Long clientId, Integer updateId) {
        ClientMessageStepEntity entity = new ClientMessageStepEntity();
        entity.setClientId(clientId);
        entity.setNextStep(ClientMessageStepType.MAKE_AN_APPOINTMENT_ADD_END);

        clientMessageStepRepository.save(entity);
        log.debug("Текущее состояние переписки запись закончена. updateId: {}, clientId: {}",
                clientId, updateId);
    }
}
