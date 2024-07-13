package ru.boris.psychologist.notebook.api.service.tg;

import ru.boris.psychologist.notebook.dto.tg.ResponseDto;
import ru.boris.psychologist.notebook.dto.tg.UpdateDto;

import java.util.Optional;

/**
 * Сервис для формирования ответа пользователю, если не удалось определить тип команды или найти ее обработчика.
 */
public interface DefaultResponseService {

    /**
     * Метод формирования ответа.
     *
     * @param dto событие полученное от пользователя.
     * @return ответ пользователю.
     */
    Optional<ResponseDto> createResponse(UpdateDto dto);
}
