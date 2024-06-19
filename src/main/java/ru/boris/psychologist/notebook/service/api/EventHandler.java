package ru.boris.psychologist.notebook.service.api;

import ru.boris.psychologist.notebook.dto.EventDto;
import ru.boris.psychologist.notebook.dto.ResponseDto;

import java.util.Optional;

/**
 * Сервис для обработки события из телеграмма.
 */
public interface EventHandler {

    Optional<ResponseDto> handle(EventDto eventDto);
}
