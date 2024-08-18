package ru.boris.psychologist.notebook.service.tg.response;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.boris.psychologist.notebook.api.service.tg.response.DefaultResponseService;
import ru.boris.psychologist.notebook.dto.tg.ChatDto;
import ru.boris.psychologist.notebook.dto.tg.MessageDto;
import ru.boris.psychologist.notebook.dto.tg.ResponseDto;
import ru.boris.psychologist.notebook.dto.tg.UpdateDto;

import java.util.Optional;

/**
 * Реализация сервиса для формирования ответа пользователю,
 * если не удалось определить тип команды или найти ее обработчика.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultResponseServiceImpl implements DefaultResponseService {

    @Override
    public Optional<ResponseDto> createResponse(UpdateDto dto) {
        Integer updateId = dto.getUpdateId();
        log.debug("Не удалось определить команду, или сформировать ответ для события с id: {}", updateId);

        Long chatId = Optional.ofNullable(dto.getMessage())
                .map(MessageDto::getChat)
                .map(ChatDto::getId)
                .orElseThrow(() -> new RuntimeException(
                        String.format("Не удалось определить идентификатор чата. messageId: %s", updateId)));

        ResponseDto response = new ResponseDto();
        response.setChatId(chatId);
        response.setText("Понятие не имею что с этим делать. " +
                "Попробуйте выбрать команду из списка доступных нажав кнопку " +
                "Меню с лева от поля ввода текста.");

        return Optional.of(response);
    }
}
