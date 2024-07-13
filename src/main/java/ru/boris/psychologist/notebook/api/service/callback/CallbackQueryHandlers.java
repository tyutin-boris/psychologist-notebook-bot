package ru.boris.psychologist.notebook.api.service.callback;

import ru.boris.psychologist.notebook.dto.tg.ResponseDto;
import ru.boris.psychologist.notebook.dto.tg.UpdateDto;
import ru.boris.psychologist.notebook.dto.tg.callback.CallbackTypes;

import java.util.Optional;

public interface CallbackQueryHandlers {

    Optional<ResponseDto> handle(UpdateDto dto);

    CallbackTypes getCallbackTypes();
}
