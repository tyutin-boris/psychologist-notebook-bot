package ru.boris.psychologist.notebook.api.service.bot;

import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Сервис для работы с событиями.
 */
public interface UpdateHistoryService {
    void saveUpdateHistory(Update update);
}
