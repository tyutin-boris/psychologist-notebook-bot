package ru.boris.psychologist.notebook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class PsychologistNotebookBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(PsychologistNotebookBotApplication.class, args);
    }

}
