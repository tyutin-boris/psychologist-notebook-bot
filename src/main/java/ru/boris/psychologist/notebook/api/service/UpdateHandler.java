package ru.boris.psychologist.notebook.api.service;

import ru.boris.psychologist.notebook.dto.tg.UpdateDto;
import ru.boris.psychologist.notebook.dto.tg.ResponseDto;

import java.util.Optional;

/**
 * Сервис для обработки события из телеграмма.
 */
public interface UpdateHandler {

    Optional<ResponseDto> handle(UpdateDto updateDto);
}
