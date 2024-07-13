package ru.boris.psychologist.notebook.mapper;

import org.mapstruct.Mapper;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import ru.boris.psychologist.notebook.dto.tg.MessageEntityDto;

@Mapper(componentModel = "spring")
public interface MessageEntityDtoMapper extends DtoToDto<MessageEntity, MessageEntityDto> {
}
