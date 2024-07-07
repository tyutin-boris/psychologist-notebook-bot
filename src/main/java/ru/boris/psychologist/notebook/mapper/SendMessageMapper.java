package ru.boris.psychologist.notebook.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.boris.psychologist.notebook.dto.ResponseDto;

import java.util.ArrayList;
import java.util.List;

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

        InlineKeyboardButton inlinekeyboardButton = new InlineKeyboardButton();
        inlinekeyboardButton.setText("Добавить номер телефона.");
        inlinekeyboardButton.setCallbackData("add_phone_number");

        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        rowsInline.add(List.of(inlinekeyboardButton));

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        markupInline.setKeyboard(rowsInline);

        return markupInline;
    }
}
