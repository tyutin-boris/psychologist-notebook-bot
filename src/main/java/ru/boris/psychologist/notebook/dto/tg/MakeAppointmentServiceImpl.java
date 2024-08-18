package ru.boris.psychologist.notebook.dto.tg;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import ru.boris.psychologist.notebook.api.service.bot.PatientService;
import ru.boris.psychologist.notebook.api.service.tg.MakeAppointmentService;

import java.util.Optional;

/**
 * Реализация сервиса для сохранения имени клиента.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MakeAppointmentServiceImpl implements MakeAppointmentService {

    private final PatientService patientService;

    @Override
    public Optional<ResponseDto> saveNameForAppointment(UpdateDto updateDto) {
        Integer updateId = updateDto.getUpdateId();
        log.debug("Запись на прием, сохраняем имя клиента.");

        Optional<MessageDto> messageOpt = Optional.ofNullable(updateDto.getMessage());

        String clientName = messageOpt
                .map(MessageDto::getText)
                .orElse(StringUtils.EMPTY);

        Optional<Long> userId = messageOpt.map(MessageDto::getFrom)
                .map(UserDto::getId);

        userId.ifPresent(id -> patientService.saveNameToContact(id, clientName));

        Optional<Long> chatIdOpt = messageOpt.map(MessageDto::getChat)
                .map(ChatDto::getId);

        if (chatIdOpt.isEmpty()) {
            log.debug("При сохранении имени клиента не удалось отправить ответ, нет идентификатора чата. updateId: {}",
                    updateId);
            return Optional.empty();
        }

        Long chatId = chatIdOpt.get();

        if (userId.isEmpty()) {
            return errorResponse(chatId);
        }

        ResponseDto responseDto = new ResponseDto();
        responseDto.setChatId(chatId);
        responseDto.setText("Укажите телефон для связи.");

        log.debug("Конец сохранения имени клиента. id: {}", updateId);
        return Optional.of(responseDto);
    }

    private Optional<ResponseDto> errorResponse(Long chatId) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setText("Не удалось сохранить ваше имя. Напишите <ТУТ ДОЛЖНА БЫТЬ ССЫЛКА>");
        responseDto.setChatId(chatId);

        return Optional.of(responseDto);
    }
}
