package ru.boris.psychologist.notebook.dto.tg;

import lombok.Data;

@Data
public class InlineKeyboardButtonDto {

    private String text;

    private String callbackData;
}
