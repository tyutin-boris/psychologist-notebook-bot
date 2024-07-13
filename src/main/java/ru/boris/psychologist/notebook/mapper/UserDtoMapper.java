package ru.boris.psychologist.notebook.mapper;

import org.mapstruct.Mapper;
import org.telegram.telegrambots.meta.api.objects.User;
import ru.boris.psychologist.notebook.dto.tg.UserDto;

@Mapper(componentModel = "spring")
public interface UserDtoMapper extends DtoToDto<User, UserDto> {
}
