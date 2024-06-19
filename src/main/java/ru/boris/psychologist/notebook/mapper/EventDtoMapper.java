package ru.boris.psychologist.notebook.mapper;

import org.mapstruct.Mapper;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.boris.psychologist.notebook.dto.EventDto;

@Mapper(componentModel = "spring")
public interface EventDtoMapper extends DtoToDto<Update, EventDto> {
}
