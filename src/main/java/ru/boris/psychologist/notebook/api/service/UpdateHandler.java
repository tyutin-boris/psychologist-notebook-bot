package ru.boris.psychologist.notebook.api.service;

import ru.boris.psychologist.notebook.dto.UpdateDto;
import ru.boris.psychologist.notebook.dto.ResponseDto;

import java.util.Optional;

/**
 * Сервис для обработки события из телеграмма.
 */
public interface UpdateHandler {

    Optional<ResponseDto> handle(UpdateDto updateDto);
}