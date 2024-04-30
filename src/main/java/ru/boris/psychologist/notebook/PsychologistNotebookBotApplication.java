package ru.boris.psychologist.notebook;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import ru.boris.psychologist.notebook.model.UserEntity;
import ru.boris.psychologist.notebook.repository.UserRepository;

@SpringBootApplication
@EnableConfigurationProperties
public class PsychologistNotebookBotApplication {

    private final UserRepository repository;

    public PsychologistNotebookBotApplication(UserRepository repository) {
        this.repository = repository;
    }

    public static void main(String[] args) {
        SpringApplication.run(PsychologistNotebookBotApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            UserEntity user = new UserEntity();
            user.setName("Boris");
            repository.save(user);
        };
    }
}
