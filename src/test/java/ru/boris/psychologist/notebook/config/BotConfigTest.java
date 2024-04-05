package ru.boris.psychologist.notebook.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(classes = BotConfig.class)
@EnableConfigurationProperties
public class BotConfigTest {

    @Autowired
    private BotConfig sut;

    @Test
    public void name() {
        // Подготовка
        String expectedName = "test-bot-name";
        String expectedToken = "test-token";

        // Действие
        String actualName = sut.getName();
        String actualToken = sut.getToken();

        // Проверка
        Assertions.assertEquals(expectedName, actualName);
        Assertions.assertEquals(expectedToken, actualToken);
    }
}
