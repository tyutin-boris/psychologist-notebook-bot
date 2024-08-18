package ru.boris.psychologist.notebook.service.tg.mesage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.boris.psychologist.notebook.api.service.domain.client.ClientMessageStepService;
import ru.boris.psychologist.notebook.api.service.tg.UserDtoService;
import ru.boris.psychologist.notebook.api.service.tg.message.DefaultMessageHandler;
import ru.boris.psychologist.notebook.api.service.tg.message.MessageHandler;
import ru.boris.psychologist.notebook.api.service.tg.message.TgMessageService;
import ru.boris.psychologist.notebook.api.service.tg.response.DefaultResponseService;
import ru.boris.psychologist.notebook.dto.domain.step.ClientMessageStepType;
import ru.boris.psychologist.notebook.dto.tg.ResponseDto;
import ru.boris.psychologist.notebook.dto.tg.UpdateDto;

import java.util.Map;
import java.util.Optional;

/**
 * Реализация сервиса для работы с message.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TgMessageServiceImpl implements TgMessageService {

    private final UserDtoService userDtoService;

    private final DefaultResponseService defaultResponseService;

    private final ClientMessageStepService clientMessageStepService;

    private final Map<ClientMessageStepType, MessageHandler> messageHandlers;

    @Override
    public Optional<ResponseDto> handleMessage(UpdateDto dto) {
        Integer updateId = dto.getUpdateId();
        Optional<Long> clientIdOpt = userDtoService.getUserId(dto);

        if (clientIdOpt.isEmpty()) {
            log.debug("Идентификатор клиента не найден. updateId: {}", updateId);
            return defaultResponseService.createResponse(dto);
        }

        Long clientId = clientIdOpt.get();
        ClientMessageStepType messageStep = clientMessageStepService.findLastMessageByClientId(clientId);

        Optional<MessageHandler> messageHandler = Optional.ofNullable(messageHandlers.get(messageStep));

        return messageHandler.flatMap(handler -> handler.handle(clientId, dto));
    }
}
