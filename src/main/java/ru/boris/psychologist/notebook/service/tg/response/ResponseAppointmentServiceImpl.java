package ru.boris.psychologist.notebook.service.tg.response;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.boris.psychologist.notebook.api.service.tg.ChatDtoService;
import ru.boris.psychologist.notebook.api.service.tg.response.ResponseAppointmentService;
import ru.boris.psychologist.notebook.dto.tg.ResponseDto;
import ru.boris.psychologist.notebook.dto.tg.UpdateDto;

import java.util.Optional;

/**
 * Реализация сервиса для.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ResponseAppointmentServiceImpl implements ResponseAppointmentService {

    private final ChatDtoService chatDtoService;

    @Override
    public Optional<ResponseDto> getNameToContactResponse(UpdateDto dto) {
        Optional<Long> chatIdOpt = chatDtoService.getChatIdOpt(dto);

        if (chatIdOpt.isEmpty()) {
            log.error("Не удалось сформировать сообщение для запроса на запись клиента updateId: {}",
                    dto.getUpdateId());
            return Optional.empty();
        }

        ResponseDto response = new ResponseDto();
        response.setText("Как к вам можно обращаться? (укажите имя)");
        response.setChatId(chatIdOpt.get());

        return Optional.of(response);
    }

    @Override
    public Optional<ResponseDto> getPhoneNumberResponse(UpdateDto dto) {
        Optional<Long> chatIdOpt = chatDtoService.getChatIdOpt(dto);

        if (chatIdOpt.isEmpty()) {
            log.debug("При сохранении имени клиента не удалось отправить ответ, нет идентификатора чата updateId: {}",
                    dto.getUpdateId());
            return Optional.empty();
        }

        ResponseDto response = new ResponseDto();
        response.setChatId(chatIdOpt.get());
        response.setText("Укажите телефон для связи.");

        return Optional.of(response);
    }

    @Override
    public Optional<ResponseDto> getPossibleCallTime(UpdateDto dto) {
        Optional<Long> chatIdOpt = chatDtoService.getChatIdOpt(dto);

        if (chatIdOpt.isEmpty()) {
            log.debug("При сохранении номера телефона клиента не удалось отправить ответ, нет идентификатора чата " +
                            "updateId: {}", dto.getUpdateId());
            return Optional.empty();
        }

        ResponseDto response = new ResponseDto();
        response.setChatId(chatIdOpt.get());
        response.setText("Укажите телефон для связи.");

        return Optional.of(response);
    }
}
