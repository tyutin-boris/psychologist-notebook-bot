package ru.boris.psychologist.notebook.service.tg.mesage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.boris.psychologist.notebook.api.mapper.history.MakeAnAppointmentService;
import ru.boris.psychologist.notebook.api.service.domain.appoitment.MakeAppointmentService;
import ru.boris.psychologist.notebook.api.service.tg.message.MessageHandler;
import ru.boris.psychologist.notebook.api.service.tg.response.ResponseAppointmentService;
import ru.boris.psychologist.notebook.dto.domain.step.ClientMessageStepType;
import ru.boris.psychologist.notebook.dto.tg.ResponseDto;
import ru.boris.psychologist.notebook.dto.tg.UpdateDto;

import java.util.Optional;

/**
 * Реализация сервиса для.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AppointmentAddNameMessageHandler implements MessageHandler {

    private final MakeAppointmentService makeAppointmentService;

    private final MakeAnAppointmentService makeAnAppointmentService;

    private final ResponseAppointmentService responseAppointmentService;


    @Override
    public Optional<ResponseDto> handle(Long clientId, UpdateDto dto) {
        Integer updateId = dto.getUpdateId();
        log.debug("Добавляем имя клиента. updateId: {}, clientId: {}", updateId, clientId);

        makeAppointmentService.saveNameForAppointment(dto);
        makeAnAppointmentService.saveNextStepAppointmentAddPhoneNumber(clientId, updateId);
        return responseAppointmentService.getPhoneNumberResponse(dto);
    }

    @Override
    public ClientMessageStepType getStepType() {
        return ClientMessageStepType.MAKE_AN_APPOINTMENT_ADD_NAME;
    }
}
