package ru.boris.psychologist.notebook.service.tg.mesage;

import ru.boris.psychologist.notebook.api.service.tg.message.MessageHandler;
import ru.boris.psychologist.notebook.dto.domain.step.ClientMessageStepType;
import ru.boris.psychologist.notebook.dto.tg.ResponseDto;
import ru.boris.psychologist.notebook.dto.tg.UpdateDto;

import java.util.Optional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Реализация сервиса для.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class NotDefinedMessageStepMessageHandler implements MessageHandler {

    @Override
    public Optional<ResponseDto> handle(Long clientId, UpdateDto dto) {
        return Optional.empty();
    }

    @Override
    public ClientMessageStepType getStepType() {
        return ClientMessageStepType.NOT_DEFINED_MESSAGE_STEP;
    }
}
