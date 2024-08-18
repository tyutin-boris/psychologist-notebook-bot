package ru.boris.psychologist.notebook.api.service.tg.message;

import ru.boris.psychologist.notebook.dto.tg.ResponseDto;
import ru.boris.psychologist.notebook.dto.tg.UpdateDto;

import java.util.Optional;

/**
 * Сервис для работы с message.
 */
public interface TgMessageService {

    Optional<ResponseDto> handleMessage(UpdateDto updateDto);
}
