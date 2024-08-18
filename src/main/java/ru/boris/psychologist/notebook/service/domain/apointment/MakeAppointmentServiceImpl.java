package ru.boris.psychologist.notebook.service.domain.apointment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import ru.boris.psychologist.notebook.api.service.domain.appoitment.AppointmentService;
import ru.boris.psychologist.notebook.api.service.domain.appoitment.MakeAppointmentService;
import ru.boris.psychologist.notebook.api.service.tg.UserDtoService;
import ru.boris.psychologist.notebook.dto.tg.MessageDto;
import ru.boris.psychologist.notebook.dto.tg.MessageEntityDto;
import ru.boris.psychologist.notebook.dto.tg.UpdateDto;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Реализация сервиса для сохранения имени клиента.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MakeAppointmentServiceImpl implements MakeAppointmentService {
    private final String phoneNumber = "phone_number";

    private final UserDtoService userDtoService;

    private final AppointmentService appointmentService;

    @Override
    public void saveNameForAppointment(UpdateDto dto) {
        Integer updateId = dto.getUpdateId();
        log.debug("Сохраняем имя клиента. updateId: {}", updateId);

        Optional<MessageDto> messageOpt = Optional.ofNullable(dto.getMessage());

        String clientName = messageOpt
                .map(MessageDto::getText)
                .orElse(StringUtils.EMPTY);

        Optional<Long> clientIdOpt = userDtoService.getUserId(dto);

        if (clientIdOpt.isEmpty()) {
            log.error("Не удалось сохранить имя клиента clientId не найден. updateId: {}", updateId);
            return;
        }

        Long clientId = clientIdOpt.get();
        log.debug("updateId: {}, clientId: {}", updateId, clientId);
        appointmentService.saveNameToContact(clientId, clientName);

        log.debug("Конец сохранения имени клиента. updateId: {}, clientId: {}", updateId, clientId);
    }

    @Override
    public void savePoneNumberForAppointment(UpdateDto dto) {
        Integer updateId = dto.getUpdateId();
        Optional<MessageEntityDto> messageEntityDto = getMessageEntitiesWithPhoneNumber(dto)
                .stream()
                .findFirst();

        if (messageEntityDto.isEmpty()) {
            log.error("В сообщении нет ни одного номера id: " + updateId);
            return;
        }

        Optional<Long> clientIdOpt = userDtoService.getUserId(dto);

        if (clientIdOpt.isEmpty()) {
            log.error("Не удалось сохранить номер клиента. id: {}", updateId);
            return;
        }

        String phoneNumber = messageEntityDto.map(MessageEntityDto::getText)
                .orElse(StringUtils.EMPTY);

        appointmentService.savePhoneNumber(clientIdOpt.get(), phoneNumber);
    }

    private List<MessageEntityDto> getMessageEntitiesWithPhoneNumber(UpdateDto dto) {
        List<MessageEntityDto> messageEntities = Optional.ofNullable(dto.getMessage())
                .map(MessageDto::getEntities)
                .orElse(Collections.emptyList());

        return messageEntities.stream()
                .filter(e -> phoneNumber.equals(e.getType()))
                .toList();
    }
}
