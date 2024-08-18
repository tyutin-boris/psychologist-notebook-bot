package ru.boris.psychologist.notebook.api.service.tg.callback;

import ru.boris.psychologist.notebook.dto.tg.ResponseDto;
import ru.boris.psychologist.notebook.dto.tg.UpdateDto;
import ru.boris.psychologist.notebook.dto.domain.callback.CallbackTypes;

import java.util.Optional;

public interface TgCallbackQueryHandlers {

    Optional<ResponseDto> handle(UpdateDto dto);

    CallbackTypes getCallbackType();
}
