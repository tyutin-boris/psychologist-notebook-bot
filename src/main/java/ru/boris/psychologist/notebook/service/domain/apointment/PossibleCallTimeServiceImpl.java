package ru.boris.psychologist.notebook.service.domain.apointment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.boris.psychologist.notebook.api.mapper.history.MakeAnAppointmentService;
import ru.boris.psychologist.notebook.api.service.domain.appoitment.MakeAppointmentService;
import ru.boris.psychologist.notebook.api.service.domain.appoitment.PossibleCallTimeService;
import ru.boris.psychologist.notebook.api.service.tg.ChatDtoService;
import ru.boris.psychologist.notebook.api.service.tg.response.DefaultResponseService;
import ru.boris.psychologist.notebook.api.service.tg.response.ResponseAppointmentService;
import ru.boris.psychologist.notebook.dto.domain.callback.PossibleCallTime;
import ru.boris.psychologist.notebook.dto.tg.ResponseDto;
import ru.boris.psychologist.notebook.dto.tg.UpdateDto;

import java.util.Optional;

/**
 * Реализация сервиса для.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PossibleCallTimeServiceImpl implements PossibleCallTimeService {

    private final ChatDtoService chatDtoService;

    private final MakeAppointmentService makeAppointmentService;

    private final DefaultResponseService defaultResponseService;

    private final MakeAnAppointmentService makeAnAppointmentService;

    private final ResponseAppointmentService responseAppointmentService;


    @Override
    public Optional<ResponseDto> handle(UpdateDto dto, PossibleCallTime possibleCallTime) {
        Optional<Long> clientIdOpt = chatDtoService.getChatIdOptFromCallbackQueryDto(dto);
        Integer updateId = dto.getUpdateId();

        if (clientIdOpt.isEmpty()) {
            log.debug("Идентификатор клиента не найден. updateId: {}", updateId);
            return defaultResponseService.createResponse(dto);
        }

        Long clientId = clientIdOpt.get();
        log.debug("Начало обработки запроса на добавление возможного времени для звонка. " +
                "updateId: {}, clientId: {}", updateId, clientId);

        makeAppointmentService.savePossibleCalltimeForAppointment(dto, possibleCallTime);
        makeAnAppointmentService.saveAppointmentAddQuestion(clientId, updateId);
        return responseAppointmentService.getQquestionResponse(dto);
    }
}
