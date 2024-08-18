package ru.boris.psychologist.notebook.service.tg.callback;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.boris.psychologist.notebook.api.service.tg.response.DefaultResponseService;
import ru.boris.psychologist.notebook.api.service.tg.callback.TgCallbackQueryHandlers;
import ru.boris.psychologist.notebook.api.service.tg.callback.TgCallbackQueryService;
import ru.boris.psychologist.notebook.dto.tg.CallbackQueryDto;
import ru.boris.psychologist.notebook.dto.tg.InlineKeyboardButtonDto;
import ru.boris.psychologist.notebook.dto.tg.InlineKeyboardMarkupDto;
import ru.boris.psychologist.notebook.dto.tg.MessageDto;
import ru.boris.psychologist.notebook.dto.tg.ResponseDto;
import ru.boris.psychologist.notebook.dto.tg.UpdateDto;
import ru.boris.psychologist.notebook.dto.domain.callback.CallbackTypes;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Реализация сервиса для работы обработки callback.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TgCallbackQueryServiceImpl implements TgCallbackQueryService {

    private final DefaultResponseService defaultResponseService;

    private final Map<CallbackTypes, TgCallbackQueryHandlers> callbackQueryHandlers;

    @Override
    public Optional<ResponseDto> handleCallbackQuery(UpdateDto updateDto) {
        Integer updateId = updateDto.getUpdateId();
        CallbackQueryDto callbackQuery = updateDto.getCallbackQuery();

        List<InlineKeyboardButtonDto> keyboardButtons = getKeyboardButtons(updateDto.getCallbackQuery());
        int keyboardButtonsSize = keyboardButtons.size();
        if (keyboardButtonsSize == 0) {
            log.error("В сообщение нет ответа на запрос. id: {}", updateId);
            return defaultResponseService.createResponse(updateDto);
        }

        CallbackTypes callbackType = CallbackTypes.getCallbackType(callbackQuery.getData());
        return Optional.ofNullable(callbackQueryHandlers.get(callbackType))
                .flatMap(handler -> handler.handle(updateDto));
    }

    private List<InlineKeyboardButtonDto> getKeyboardButtons(CallbackQueryDto callbackQuery) {
        List<List<InlineKeyboardButtonDto>> keyboardButtonsList = Optional.ofNullable(callbackQuery.getMessage())
                .map(MessageDto::getReplyMarkup)
                .map(InlineKeyboardMarkupDto::getKeyboard)
                .orElse(Collections.emptyList());

        return keyboardButtonsList.stream()
                .flatMap(List::stream)
                .toList();
    }
}
