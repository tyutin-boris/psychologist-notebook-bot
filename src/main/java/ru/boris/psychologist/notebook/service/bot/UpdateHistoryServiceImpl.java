package ru.boris.psychologist.notebook.service.bot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.boris.psychologist.notebook.api.repository.history.UpdateHistoryRepository;
import ru.boris.psychologist.notebook.api.service.bot.UpdateHistoryService;
import ru.boris.psychologist.notebook.entity.history.UpdateHistoryEntity;

/**
 * Реализация сервиса для работы с событиями.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UpdateHistoryServiceImpl implements UpdateHistoryService {

    private final ObjectMapper objectMapper;

    private final UpdateHistoryRepository updateHistoryRepository;

    @Override
    public void saveUpdateHistory(Update update) {
        String json = getString(update);
        UpdateHistoryEntity entity = new UpdateHistoryEntity();
        entity.setJson(json);
        updateHistoryRepository.save(entity);
    }

    private String getString(Update update) {
        try {
            return objectMapper.writeValueAsString(update);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
