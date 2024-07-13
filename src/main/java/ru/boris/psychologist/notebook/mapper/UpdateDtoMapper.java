package ru.boris.psychologist.notebook.mapper;

import org.mapstruct.Mapper;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.boris.psychologist.notebook.dto.tg.UpdateDto;

@Mapper(componentModel = "spring", uses = {MessageDtoMapper.class, CallbackQueryDtoMapper.class})
public interface UpdateDtoMapper extends DtoToDto<Update, UpdateDto> {

    @Override
    UpdateDto toDto(Update update);
}
