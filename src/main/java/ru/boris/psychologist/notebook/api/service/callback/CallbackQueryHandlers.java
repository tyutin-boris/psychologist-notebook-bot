package ru.boris.psychologist.notebook.api.service.callback;

import ru.boris.psychologist.notebook.dto.ResponseDto;
import ru.boris.psychologist.notebook.dto.UpdateDto;
import ru.boris.psychologist.notebook.dto.callback.CallbackTypes;

import java.util.Optional;

public interface CallbackQueryHandlers {

    Optional<ResponseDto> handle(UpdateDto dto);

    CallbackTypes getCallbackTypes();
}
