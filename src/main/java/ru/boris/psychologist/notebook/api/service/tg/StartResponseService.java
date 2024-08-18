package ru.boris.psychologist.notebook.api.service.tg;

import ru.boris.psychologist.notebook.dto.tg.MessageDto;
import ru.boris.psychologist.notebook.dto.tg.ResponseDto;

public interface StartResponseService {

    ResponseDto getStartResponseDto(MessageDto dto, Long chatId);
}
