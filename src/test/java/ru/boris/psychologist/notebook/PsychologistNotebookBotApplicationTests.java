package ru.boris.psychologist.notebook;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import ru.boris.psychologist.notebook.config.TestContextConfig;

@ActiveProfiles("test")
@SpringBootTest(classes = TestContextConfig.class)
class PsychologistNotebookBotApplicationTests {

    private static final PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:14");

    @BeforeAll
    public static void start() {
        container.start();
    }

//    @AfterAll
//    public static void stop() {
//        container.stop();
//    }

    @DynamicPropertySource
    public static void configureProperty(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.username", container::getUsername);
        registry.add("spring.datasource.password", container::getPassword);
    }

    @Test
    void contextLoads() {
    }
}
