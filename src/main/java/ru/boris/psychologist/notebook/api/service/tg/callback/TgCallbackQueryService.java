package ru.boris.psychologist.notebook.api.service.tg.callback;

import ru.boris.psychologist.notebook.dto.tg.CallbackQueryDto;
import ru.boris.psychologist.notebook.dto.tg.ResponseDto;
import ru.boris.psychologist.notebook.dto.tg.UpdateDto;

import java.util.Optional;

/**
 * Сервис для работы обработки callback.
 */
public interface TgCallbackQueryService {
    Optional<ResponseDto> handleCallbackQuery(UpdateDto updateDto);
}
