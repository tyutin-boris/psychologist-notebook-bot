package ru.boris.psychologist.notebook.service.domain.apointment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.boris.psychologist.notebook.api.repository.appointment.AppointmentRepository;
import ru.boris.psychologist.notebook.api.service.domain.appoitment.AppointmentService;

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
}
