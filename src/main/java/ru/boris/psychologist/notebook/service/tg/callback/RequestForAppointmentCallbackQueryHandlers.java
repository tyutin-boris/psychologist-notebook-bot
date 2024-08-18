package ru.boris.psychologist.notebook.service.tg.callback;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.boris.psychologist.notebook.api.mapper.tg.message.history.PatientMessageHistoryService;
import ru.boris.psychologist.notebook.api.service.tg.callback.CallbackQueryHandlers;
import ru.boris.psychologist.notebook.dto.tg.CallbackQueryDto;
import ru.boris.psychologist.notebook.dto.tg.ChatDto;
import ru.boris.psychologist.notebook.dto.tg.MessageDto;
import ru.boris.psychologist.notebook.dto.tg.ResponseDto;
import ru.boris.psychologist.notebook.dto.tg.UpdateDto;
import ru.boris.psychologist.notebook.dto.tg.callback.CallbackTypes;

import java.util.Optional;

/**
 * Реализация сервиса для записи на прием.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class RequestForAppointmentCallbackQueryHandlers implements CallbackQueryHandlers {

    private final PatientMessageHistoryService patientMessageHistoryService;

    @Override
    public Optional<ResponseDto> handle(UpdateDto dto) {
        Integer updateId = dto.getUpdateId();
        log.debug("Получен запрос записи на прием. updateId: {}", updateId);
        Long chatId = Optional.ofNullable(dto.getCallbackQuery())
                .map(CallbackQueryDto::getMessage)
                .map(MessageDto::getChat)
                .map(ChatDto::getId)
                .orElseThrow(() -> new RuntimeException(
                        String.format("Не удалось определить идентификатор чата. updateId: %s", updateId)));

        Optional<Long> patientId = Optional.of(dto)
                .map(UpdateDto::getCallbackQuery)
                .map(CallbackQueryDto::getMessage)
                .map(MessageDto::getChat)
                .map(ChatDto::getId);

        if (patientId.isEmpty()) {
            log.error("Не удалось сохранить запрос записи на прием," +
                    " у пользователя нет идентификатора. updateId: {}", updateId);
        }

        patientId.ifPresent(id -> patientMessageHistoryService.saveRequestForAppointment(id, updateId));

        ResponseDto response = new ResponseDto();
        response.setText("Как к вам можно обращаться? (укажите имя)");
        response.setChatId(chatId);

        return Optional.of(response);
    }

    @Override
    public CallbackTypes getCallbackTypes() {
        return CallbackTypes.MAKE_AN_APPOINTMENT;
    }
}
