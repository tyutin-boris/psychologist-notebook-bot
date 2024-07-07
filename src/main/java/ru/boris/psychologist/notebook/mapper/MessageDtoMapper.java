package ru.boris.psychologist.notebook.mapper;

import org.mapstruct.Mapper;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.boris.psychologist.notebook.dto.MessageDto;

@Mapper(componentModel = "spring", uses = {
        UserDtoMapper.class,
        ChatDtoMapper.class,
        InlineKeyboardMarkupDtoMapper.class})
public interface MessageDtoMapper extends DtoToDto<Message, MessageDto> {

}
