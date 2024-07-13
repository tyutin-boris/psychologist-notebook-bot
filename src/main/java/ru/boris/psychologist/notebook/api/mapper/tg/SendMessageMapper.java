package ru.boris.psychologist.notebook.api.mapper.tg;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.boris.psychologist.notebook.dto.tg.ReplyKeyboardDto;
import ru.boris.psychologist.notebook.dto.tg.ResponseDto;
import ru.boris.psychologist.notebook.api.mapper.DtoToDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Mapper(componentModel = "spring")
public interface SendMessageMapper extends DtoToDto<ResponseDto, SendMessage> {

    @Mapping(target = "replyToMessageId", ignore = true)
    @Mapping(target = "replyMarkup", source = ".", qualifiedByName = "getReplyMarkup")
    @Mapping(target = "protectContent", ignore = true)
    @Mapping(target = "parseMode", ignore = true)
    @Mapping(target = "messageThreadId", ignore = true)
    @Mapping(target = "entities", ignore = true)
    @Mapping(target = "disableWebPagePreview", ignore = true)
    @Mapping(target = "disableNotification", ignore = true)
    @Mapping(target = "allowSendingWithoutReply", ignore = true)
    @Override
    SendMessage toDto(ResponseDto responseDto);

    @Named("getReplyMarkup")
    default InlineKeyboardMarkup getReplyMarkup(ResponseDto responseDto) {

        if (Objects.isNull(responseDto)) {
            return null;
        }

        ReplyKeyboardDto replyMarkup = responseDto.getReplyMarkup();

        if (Objects.isNull(replyMarkup)) {
            return null;
        }

        InlineKeyboardButton inlinekeyboardButton = new InlineKeyboardButton();
        inlinekeyboardButton.setText(replyMarkup.getText());
        inlinekeyboardButton.setCallbackData(replyMarkup.getCallbackData());

        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        rowsInline.add(List.of(inlinekeyboardButton));

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        markupInline.setKeyboard(rowsInline);

        return markupInline;
    }
}
