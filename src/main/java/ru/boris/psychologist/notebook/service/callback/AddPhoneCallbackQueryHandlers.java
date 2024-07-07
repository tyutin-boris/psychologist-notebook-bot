package ru.boris.psychologist.notebook.service.callback;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.boris.psychologist.notebook.api.service.callback.CallbackQueryHandlers;
import ru.boris.psychologist.notebook.dto.CallbackQueryDto;
import ru.boris.psychologist.notebook.dto.ChatDto;
import ru.boris.psychologist.notebook.dto.MessageDto;
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
        Integer updateId = dto.getUpdateId();
        Long chatId = Optional.ofNullable(dto.getCallbackQuery())
                .map(CallbackQueryDto::getMessage)
                .map(MessageDto::getChat)
                .map(ChatDto::getId)
                .orElseThrow(() -> new RuntimeException(
                        String.format("Не удалось определить идентификатор чата. messageId: %s", updateId)));

        ResponseDto response = new ResponseDto();
        response.setText("Укажите пожалуйста ваш номер телефона по которому можно с вами связаться.");
        response.setChatId(chatId);

        return Optional.of(response);
    }

    @Override
    public CallbackTypes getCallbackTypes() {
        return CallbackTypes.ADD_PHONE_NUMBER;
    }
}
