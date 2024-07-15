package ru.boris.psychologist.notebook.dto.tg;

import lombok.Data;

@Data
public class CallbackQueryDto {

    private MessageDto message;

    private String data;
}
