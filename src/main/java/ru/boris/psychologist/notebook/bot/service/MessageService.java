package ru.boris.psychologist.notebook.bot.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface MessageService {

    SendMessage getMessage(Update update);
}
