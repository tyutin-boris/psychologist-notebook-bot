package ru.boris.psychologist.notebook.api.service.tg.command;

import ru.boris.psychologist.notebook.dto.tg.MessageEntityDto;
import ru.boris.psychologist.notebook.dto.tg.ResponseDto;
import ru.boris.psychologist.notebook.dto.tg.UpdateDto;

import java.util.List;
import java.util.Optional;

/**
 * Сервис для работы с bot_command.
 */
public interface TgCommandService {

    Optional<ResponseDto> handlerCommand(UpdateDto updateDto, List<MessageEntityDto> messageEntitiesWithCommand);

    List<MessageEntityDto> getMessageEntitiesWithCommand(UpdateDto updateDto);
}
