package ru.boris.psychologist.notebook.service.domain.apointment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.boris.psychologist.notebook.api.repository.appointment.AppointmentRepository;
import ru.boris.psychologist.notebook.api.service.domain.appoitment.AppointmentService;
import ru.boris.psychologist.notebook.dto.domain.callback.PossibleCallTime;
import ru.boris.psychologist.notebook.entity.appointment.AppointmentEntity;

/**
 * Реализация сервиса для.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;

    @Override
    @Transactional
    public void saveNameToContact(Long clientId, String nameToContact) {
        log.debug("Попытка сохранить имя для обращения. clientId: {}", clientId);
        appointmentRepository.updateNameToContactByTgId(nameToContact, clientId);
        log.debug("Имя для обращения сохранено. clientId: {}", clientId);
    }

    @Override
    @Transactional
    public void savePhoneNumber(Long clientId, String phoneNumber) {
        log.debug("Попытка сохранить номер телефона для обращения. tgId: {}", clientId);
        appointmentRepository.updatePhoneNumberByTgId(phoneNumber, clientId);
        log.debug("Номер телефона для обращения сохранено. tgId: {}", clientId);
    }

    @Override
    @Transactional
    public void saveQuestion(Long clientId, String question) {
        log.debug("Попытка сохранить вопрос клиента. tgId: {}", clientId);
        appointmentRepository.updateQuestionByTgId(question, clientId);
        log.debug("Вопрос клиента для обращения сохранен. tgId: {}", clientId);
    }

    @Override
    @Transactional
    public void savePossibleCallTime(Long clientId, PossibleCallTime possibleCallTime) {
        log.debug("Попытка сохранить время звонка. tgId: {}", clientId);
        appointmentRepository.updatePossibleCallTime(possibleCallTime.getTime(), clientId);
        log.debug("Время звонка для обращения сохранен. tgId: {}", clientId);
    }

    @Override
    @Transactional
    public void saveAppointment(Long clientId) {
        log.debug("Создаем новое обращение clientId: {}", clientId);
        AppointmentEntity entity = new AppointmentEntity();

        entity.setTgId(clientId);

        appointmentRepository.save(entity);
        log.debug("Обращение создано clientId: {}", clientId);
    }
}
