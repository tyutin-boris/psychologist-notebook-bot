package ru.boris.psychologist.notebook.api.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import ru.boris.psychologist.notebook.bot.container.BotTestContainer;
import ru.boris.psychologist.notebook.model.entity.UpdateHistoryEntity;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UpdateHistoryRepositoryTest extends BotTestContainer {

    @Autowired
    private UpdateHistoryRepository sut;

    @Test
    @DisplayName("Должен сохранить запись")
    public void should_saveEntity() {
        // Подготовка
        String json = "{\"key\": \"value\"}";

        UpdateHistoryEntity entity = new UpdateHistoryEntity();
        entity.setJson(json);

        // Действие
        sut.save(entity);

        // Проверка
        UpdateHistoryEntity actual = sut.findAll().stream()
                .findFirst()
                .orElse(null);

        assertThat(actual).isNotNull();
        assertThat(actual.getJson()).isEqualTo(json);
        assertThat(actual.getCreateDateTime()).isNotNull();
    }
}

