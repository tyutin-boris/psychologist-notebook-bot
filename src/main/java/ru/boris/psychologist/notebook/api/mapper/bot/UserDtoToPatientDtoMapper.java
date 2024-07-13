package ru.boris.psychologist.notebook.api.mapper.bot;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.boris.psychologist.notebook.api.mapper.DtoToDto;
import ru.boris.psychologist.notebook.dto.bot.PatientDto;
import ru.boris.psychologist.notebook.dto.tg.UserDto;

@Mapper(componentModel = "spring")
public interface UserDtoToPatientDtoMapper extends DtoToDto<UserDto, PatientDto> {


    @Mapping(target = "username", source = "userName")
    @Mapping(target = "phoneNumber", ignore = true)
    @Mapping(target = "description", ignore = true)
    @Override
    PatientDto toDto(UserDto userDto);
}
