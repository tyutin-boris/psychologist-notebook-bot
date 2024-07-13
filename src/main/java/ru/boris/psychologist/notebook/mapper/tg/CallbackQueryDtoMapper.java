package ru.boris.psychologist.notebook.mapper.tg;

import org.mapstruct.Mapper;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import ru.boris.psychologist.notebook.dto.tg.CallbackQueryDto;
import ru.boris.psychologist.notebook.mapper.DtoToDto;

@Mapper(componentModel = "spring", uses = MessageDtoMapper.class)
public interface CallbackQueryDtoMapper extends DtoToDto<CallbackQuery, CallbackQueryDto> {
}
