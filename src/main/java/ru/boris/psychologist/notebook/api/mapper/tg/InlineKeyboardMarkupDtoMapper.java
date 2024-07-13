package ru.boris.psychologist.notebook.api.mapper.tg;

import org.mapstruct.Mapper;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import ru.boris.psychologist.notebook.dto.tg.InlineKeyboardMarkupDto;
import ru.boris.psychologist.notebook.api.mapper.DtoToDto;

@Mapper(componentModel = "spring", uses = InlineKeyboardButtonDtoMapper.class)
public interface InlineKeyboardMarkupDtoMapper extends DtoToDto<InlineKeyboardMarkup, InlineKeyboardMarkupDto> {
}
