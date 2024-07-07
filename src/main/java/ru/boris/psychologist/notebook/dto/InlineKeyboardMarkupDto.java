package ru.boris.psychologist.notebook.dto;

import lombok.Data;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

@Data
public class InlineKeyboardMarkupDto {

    private List<List<InlineKeyboardButtonDto>> keyboard;
}
