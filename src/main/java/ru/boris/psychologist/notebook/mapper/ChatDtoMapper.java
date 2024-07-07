package ru.boris.psychologist.notebook.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.telegram.telegrambots.meta.api.objects.Chat;
import ru.boris.psychologist.notebook.dto.ChatDto;
import ru.boris.psychologist.notebook.dto.ChatType;

@Mapper(componentModel = "spring")
public interface ChatDtoMapper extends DtoToDto<Chat, ChatDto> {

    @Mapping(target = "type", source = "type", qualifiedByName = "getChatType")
    @Override
    ChatDto toDto(Chat chat);

    @Named("getChatType")
    default ChatType getChatType(String type) {
        return ChatType.stringToChatType(type);
    }
}
