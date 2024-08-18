package ru.boris.psychologist.notebook.service.tg.callback;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.boris.psychologist.notebook.api.service.tg.DefaultResponseService;
import ru.boris.psychologist.notebook.api.service.tg.callback.CallbackQueryHandlers;
import ru.boris.psychologist.notebook.api.service.tg.callback.TgCallbackQueryService;
import ru.boris.psychologist.notebook.dto.tg.CallbackQueryDto;
import ru.boris.psychologist.notebook.dto.tg.InlineKeyboardButtonDto;
import ru.boris.psychologist.notebook.dto.tg.InlineKeyboardMarkupDto;
import ru.boris.psychologist.notebook.dto.tg.MessageDto;
import ru.boris.psychologist.notebook.dto.tg.ResponseDto;
import ru.boris.psychologist.notebook.dto.tg.UpdateDto;
import ru.boris.psychologist.notebook.dto.tg.callback.CallbackTypes;

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

    private final Map<CallbackTypes, CallbackQueryHandlers> callbackQueryHandlers;

    @Override
    public Optional<ResponseDto> handleCallbackQuery(UpdateDto updateDto) {
        Integer updateId = updateDto.getUpdateId();

        CallbackQueryDto callbackQuery = updateDto.getCallbackQuery();

        List<List<InlineKeyboardButtonDto>> keyboardButtonsList = getKeyboardButtonsList(callbackQuery);
        List<InlineKeyboardButtonDto> keyboardButtons = getKeyboardButtons(keyboardButtonsList);

        int keyboardButtonsSize = keyboardButtons.size();

        if (keyboardButtonsSize == 0) {
            log.error("В сообщение нет ответа на запрос. id: {}", updateId);
            return defaultResponseService.createResponse(updateDto);
        }

        CallbackTypes callbackType = CallbackTypes.getCallbackType(callbackQuery.getData());

        return Optional.ofNullable(callbackQueryHandlers.get(callbackType))
                .flatMap(handler -> handler.handle(updateDto));
    }

    private List<InlineKeyboardButtonDto> getKeyboardButtons(List<List<InlineKeyboardButtonDto>> keyboardButtonsList) {
        return keyboardButtonsList.stream()
                .flatMap(List::stream)
                .toList();
    }

    private List<List<InlineKeyboardButtonDto>> getKeyboardButtonsList(CallbackQueryDto callbackQuery) {
        return Optional.ofNullable(callbackQuery.getMessage())
                .map(MessageDto::getReplyMarkup)
                .map(InlineKeyboardMarkupDto::getKeyboard)
                .orElse(Collections.emptyList());
    }
}
