package ru.boris.psychologist.notebook.mapper;

import org.mapstruct.Mapper;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.boris.psychologist.notebook.dto.ResponseDto;

@Mapper(componentModel = "spring")
public interface SendMessageMapper extends DtoToDto<ResponseDto, SendMessage> {
}
