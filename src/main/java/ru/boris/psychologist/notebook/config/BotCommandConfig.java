package ru.boris.psychologist.notebook.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import ru.boris.psychologist.notebook.dto.command.BotCommands;

import java.util.List;

@Configuration
public class BotCommandConfig {

    @Bean
    public List<BotCommand> botCommands() {
        return List.of(
                new BotCommand(BotCommands.START.getCommand(), BotCommands.START.getDescription())
        );
    }
}
