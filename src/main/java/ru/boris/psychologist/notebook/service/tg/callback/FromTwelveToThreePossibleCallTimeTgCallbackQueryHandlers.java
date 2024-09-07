package ru.boris.psychologist.notebook.service.tg.callback;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.boris.psychologist.notebook.api.service.domain.appoitment.PossibleCallTimeService;
import ru.boris.psychologist.notebook.api.service.tg.callback.TgCallbackQueryHandlers;
import ru.boris.psychologist.notebook.dto.domain.callback.CallbackTypes;
import ru.boris.psychologist.notebook.dto.domain.callback.PossibleCallTime;
import ru.boris.psychologist.notebook.dto.tg.ResponseDto;
import ru.boris.psychologist.notebook.dto.tg.UpdateDto;

import java.util.Optional;

/**
 * Реализация сервиса для.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class FromTwelveToThreePossibleCallTimeTgCallbackQueryHandlers implements TgCallbackQueryHandlers {

    private final PossibleCallTimeService possibleCallTimeService;

    @Override
    public Optional<ResponseDto> handle(UpdateDto dto) {
        return possibleCallTimeService.handle(dto, PossibleCallTime.FROM_TWELVE_TO_THREE);
    }

    @Override
    public CallbackTypes getCallbackType() {
        return CallbackTypes.FROM_TWELVE_TO_THREE;
    }
}
