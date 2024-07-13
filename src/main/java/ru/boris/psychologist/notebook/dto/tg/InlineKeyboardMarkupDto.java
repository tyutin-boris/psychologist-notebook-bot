package ru.boris.psychologist.notebook.dto.tg;

import lombok.Data;

import java.util.List;

@Data
public class InlineKeyboardMarkupDto {

    private List<List<InlineKeyboardButtonDto>> keyboard;
}
