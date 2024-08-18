package ru.boris.psychologist.notebook.api.mapper.client;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.boris.psychologist.notebook.api.mapper.DtoToDto;
import ru.boris.psychologist.notebook.dto.domain.ClientDto;
import ru.boris.psychologist.notebook.dto.tg.UserDto;

@Mapper(componentModel = "spring")
public interface UserDtoToClientDtoMapper extends DtoToDto<UserDto, ClientDto> {


    @Mapping(target = "tgId", source = "id")
    @Mapping(target = "username", source = "userName")
    @Mapping(target = "phoneNumber", ignore = true)
    @Mapping(target = "description", ignore = true)
    @Override
    ClientDto toDto(UserDto userDto);
}
