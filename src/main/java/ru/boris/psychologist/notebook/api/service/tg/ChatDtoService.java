package ru.boris.psychologist.notebook.api.service.tg;

import ru.boris.psychologist.notebook.dto.tg.UpdateDto;

import java.util.Optional;

/**
 * Сервис для работы с ChatDto.
 */
public interface ChatDtoService {

    Optional<Long> getChatIdOpt(UpdateDto dto);

    Optional<Long> getChatIdOptFromCallbackQueryDto(UpdateDto dto);
}
