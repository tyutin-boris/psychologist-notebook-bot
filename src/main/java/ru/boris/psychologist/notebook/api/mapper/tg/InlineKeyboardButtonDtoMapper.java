package ru.boris.psychologist.notebook.api.mapper.tg;

import org.mapstruct.Mapper;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.boris.psychologist.notebook.dto.tg.InlineKeyboardButtonDto;
import ru.boris.psychologist.notebook.api.mapper.DtoListListToDtoListList;
import ru.boris.psychologist.notebook.api.mapper.DtoToDto;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface InlineKeyboardButtonDtoMapper extends DtoToDto<InlineKeyboardButton, InlineKeyboardButtonDto>,
        DtoListListToDtoListList<InlineKeyboardButton, InlineKeyboardButtonDto> {

    @Override
    default List<List<InlineKeyboardButtonDto>> toDtoList(List<List<InlineKeyboardButton>> froms) {
        List<List<InlineKeyboardButtonDto>> to = new ArrayList<>();

        for (List<InlineKeyboardButton> from : froms) {
            List<InlineKeyboardButtonDto> markupDtos = new ArrayList<>();

            for (InlineKeyboardButton inlineKeyboardMarkup : from) {
                markupDtos.add(toDto(inlineKeyboardMarkup));
            }

            to.add(markupDtos);
        }

        return to;
    }
}
