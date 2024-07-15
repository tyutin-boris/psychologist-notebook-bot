package ru.boris.psychologist.notebook.service.tg;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.boris.psychologist.notebook.api.service.tg.callback.CallbackQueryHandlers;
import ru.boris.psychologist.notebook.dto.tg.CallbackQueryDto;
import ru.boris.psychologist.notebook.dto.tg.ChatDto;
import ru.boris.psychologist.notebook.dto.tg.MessageDto;
import ru.boris.psychologist.notebook.dto.tg.ResponseDto;
import ru.boris.psychologist.notebook.dto.tg.UpdateDto;
import ru.boris.psychologist.notebook.dto.tg.callback.CallbackTypes;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AddDescriptionCallbackQueryHandlers implements CallbackQueryHandlers {

    @Override
    public Optional<ResponseDto> handle(UpdateDto dto) {
        Integer updateId = dto.getUpdateId();
        log.debug("Получен запрос на добавление описания проблемы. updateId: {}", updateId);

        Long chatId = Optional.ofNullable(dto.getCallbackQuery())
                .map(CallbackQueryDto::getMessage)
                .map(MessageDto::getChat)
                .map(ChatDto::getId)
                .orElseThrow(() -> new RuntimeException(
                        String.format("Не удалось определить идентификатор чата. updateId: %s", updateId)));

        ResponseDto response = new ResponseDto();
        response.setText("Опишите пожалуйста вашу проблему.");
        response.setChatId(chatId);

        return Optional.of(response);
    }

    @Override
    public CallbackTypes getCallbackTypes() {
        return CallbackTypes.ADD_DESCRIPTION;
    }
}
