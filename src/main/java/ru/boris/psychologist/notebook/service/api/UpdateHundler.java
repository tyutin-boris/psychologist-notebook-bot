package ru.boris.psychologist.notebook.service.api;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface UpdateHundler {

    SendMessage handle(Update update);
}
