package ru.boris.psychologist.notebook.api.mapper.tg;

import org.mapstruct.Mapper;
import org.telegram.telegrambots.meta.api.objects.User;
import ru.boris.psychologist.notebook.dto.tg.UserDto;
import ru.boris.psychologist.notebook.api.mapper.DtoToDto;

@Mapper(componentModel = "spring")
public interface UserDtoMapper extends DtoToDto<User, UserDto> {
}
