package ru.boris.psychologist.notebook.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.assertj.core.api.Assertions.assertThat;

@SpringJUnitConfig(classes = EventDtoMapperImpl.class)
class EventDtoMapperTest {

    @Autowired
    private EventDtoMapper sut;

    @Test
    public void name() {
        // Подготовка


        // Действие
        assertThat(sut).isNotNull();

        // Проверка

    }
}