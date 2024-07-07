package ru.boris.psychologist.notebook.dto;

import lombok.Data;

@Data
public class InlineKeyboardButtonDto {

    private String text;

    private String callbackData;
}
