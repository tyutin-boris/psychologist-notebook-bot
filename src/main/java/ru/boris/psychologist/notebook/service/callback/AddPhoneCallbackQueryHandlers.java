package ru.boris.psychologist.notebook.service.callback;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.boris.psychologist.notebook.api.service.callback.CallbackQueryHandlers;
import ru.boris.psychologist.notebook.dto.ResponseDto;
import ru.boris.psychologist.notebook.dto.UpdateDto;
import ru.boris.psychologist.notebook.dto.callback.CallbackTypes;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AddPhoneCallbackQueryHandlers implements CallbackQueryHandlers {

    @Override
    public Optional<ResponseDto> handle(UpdateDto dto) {
        return Optional.empty();
    }

    @Override
    public CallbackTypes getCallbackTypes() {
        return CallbackTypes.ADD_PHONE_NUMBER;
    }
}
