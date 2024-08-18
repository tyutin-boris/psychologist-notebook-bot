package ru.boris.psychologist.notebook.service.tg;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.boris.psychologist.notebook.api.service.tg.response.DefaultResponseService;
import ru.boris.psychologist.notebook.api.service.tg.UpdateHandler;
import ru.boris.psychologist.notebook.api.service.tg.callback.TgCallbackQueryService;
import ru.boris.psychologist.notebook.api.service.tg.command.TgCommandService;
import ru.boris.psychologist.notebook.api.service.tg.message.TgMessageService;
import ru.boris.psychologist.notebook.dto.tg.MessageEntityDto;
import ru.boris.psychologist.notebook.dto.tg.ResponseDto;
import ru.boris.psychologist.notebook.dto.tg.UpdateDto;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UpdateHandlerImpl implements UpdateHandler {

    private final TgCommandService tgCommandService;

    private final TgMessageService tgMessageService;

    private final TgCallbackQueryService tgCallbackQueryService;

    private final DefaultResponseService defaultResponseService;

    @Override
    public Optional<ResponseDto> handle(UpdateDto updateDto) {
        if (updateDto == null) {
            log.error("Невозможно обработать событие если оно null");
            return Optional.empty();
        }

        List<MessageEntityDto> messageEntitiesWithCommand = tgCommandService.getMessageEntitiesWithCommand(updateDto);
        if (messageEntitiesIsPresent(messageEntitiesWithCommand)) {
            return tgCommandService.handlerCommand(updateDto, messageEntitiesWithCommand);
        }

        if (messageIsPresent(updateDto)) {
            return tgMessageService.handleMessage(updateDto);
        }

        if (callbackQueryIsPresent(updateDto)) {
            return tgCallbackQueryService.handleCallbackQuery(updateDto);
        }

        return defaultResponseService.createResponse(updateDto);
    }

    private boolean callbackQueryIsPresent(UpdateDto updateDto) {
        return Objects.nonNull(updateDto.getCallbackQuery());
    }

    private boolean messageIsPresent(UpdateDto updateDto) {
        return Objects.nonNull(updateDto.getMessage());
    }

    private boolean messageEntitiesIsPresent(List<MessageEntityDto> messageEntitiesWithCommand) {
        return messageEntitiesWithCommand.size() != 0;
    }
}
