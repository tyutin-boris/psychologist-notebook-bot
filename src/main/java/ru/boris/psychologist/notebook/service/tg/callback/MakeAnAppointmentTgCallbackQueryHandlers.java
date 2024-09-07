package ru.boris.psychologist.notebook.service.tg.callback;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.boris.psychologist.notebook.api.mapper.history.MakeAnAppointmentService;
import ru.boris.psychologist.notebook.api.service.domain.appoitment.AppointmentService;
import ru.boris.psychologist.notebook.api.service.tg.ChatDtoService;
import ru.boris.psychologist.notebook.api.service.tg.callback.TgCallbackQueryHandlers;
import ru.boris.psychologist.notebook.api.service.tg.response.DefaultResponseService;
import ru.boris.psychologist.notebook.api.service.tg.response.ResponseAppointmentService;
import ru.boris.psychologist.notebook.dto.domain.callback.CallbackTypes;
import ru.boris.psychologist.notebook.dto.tg.ResponseDto;
import ru.boris.psychologist.notebook.dto.tg.UpdateDto;

import java.util.Optional;

/**
 * Реализация сервиса для записи на прием.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MakeAnAppointmentTgCallbackQueryHandlers implements TgCallbackQueryHandlers {

    private final ChatDtoService chatDtoService;

    private final AppointmentService appointmentService;

    private final DefaultResponseService defaultResponseService;

    private final ResponseAppointmentService responseAppointmentService;

    private final MakeAnAppointmentService makeAnAppointmentService;

    @Override
    public Optional<ResponseDto> handle(UpdateDto dto) {
        Integer updateId = dto.getUpdateId();
        log.debug("Начата запись на прием. updateId: {}", updateId);

        Optional<Long> clientIdOpt = chatDtoService.getChatIdOptFromCallbackQueryDto(dto);

        if (clientIdOpt.isEmpty()) {
            log.error("Не удалось сохранить запрос записи на прием," +
                    " у пользователя нет идентификатора. updateId: {}", updateId);

            return defaultResponseService.createResponse(dto);
        }

        Long clientId = clientIdOpt.get();
        log.debug("Клиент. updateId: {}, clientId: {}", updateId, clientId);

        appointmentService.saveAppointment(clientId);
        makeAnAppointmentService.saveNextStepAppointmentAddName(clientId, updateId);
        Optional<ResponseDto> response = responseAppointmentService.getNameToContactResponse(dto);

        log.debug("Отправлен запрос на получение имени. updateId: {}, clientId: {}", updateId, clientId);
        return response;
    }

    @Override
    public CallbackTypes getCallbackType() {
        return CallbackTypes.MAKE_AN_APPOINTMENT;
    }
}
