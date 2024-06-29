package ru.boris.psychologist.notebook.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.boris.psychologist.notebook.dto.ResponseDto;

@Mapper(componentModel = "spring")
public interface SendMessageMapper extends DtoToDto<ResponseDto, SendMessage> {

    @Mapping(target = "replyToMessageId", ignore = true)
    @Mapping(target = "replyMarkup", ignore = true)
    @Mapping(target = "protectContent", ignore = true)
    @Mapping(target = "parseMode", ignore = true)
    @Mapping(target = "messageThreadId", ignore = true)
    @Mapping(target = "entities", ignore = true)
    @Mapping(target = "disableWebPagePreview", ignore = true)
    @Mapping(target = "disableNotification", ignore = true)
    @Mapping(target = "allowSendingWithoutReply", ignore = true)
    @Override
    SendMessage toDto(ResponseDto responseDto);
}
