package ru.boris.psychologist.notebook.mapper;

import org.mapstruct.Mapper;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import ru.boris.psychologist.notebook.dto.CallbackQueryDto;

@Mapper(componentModel = "spring", uses = MessageDtoMapper.class)
public interface CallbackQueryDtoMapper extends DtoToDto<CallbackQuery, CallbackQueryDto> {
}