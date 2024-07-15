package ru.boris.psychologist.notebook.api.mapper.tg;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.boris.psychologist.notebook.api.mapper.DtoToDto;
import ru.boris.psychologist.notebook.dto.tg.ReplyKeyboardDto;
import ru.boris.psychologist.notebook.dto.tg.ResponseDto;

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

        List<ReplyKeyboardDto> replyMarkups = responseDto.getReplyMarkup();

        if (replyMarkups == null || replyMarkups.isEmpty()) {
            return null;
        }

        List<InlineKeyboardButton> inlineKeyboardButtons = replyMarkups.stream()
                .map(this::getInlineKeyboardButton)
                .toList();

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        markupInline.setKeyboard(List.of(inlineKeyboardButtons));

        return markupInline;
    }

    default InlineKeyboardButton getInlineKeyboardButton(ReplyKeyboardDto dto) {
        InlineKeyboardButton inlinekeyboardButton = new InlineKeyboardButton();
        inlinekeyboardButton.setText(dto.getText());
        inlinekeyboardButton.setCallbackData(dto.getCallbackData());

        return inlinekeyboardButton;
    }
}
