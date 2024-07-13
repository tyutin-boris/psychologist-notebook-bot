package ru.boris.psychologist.notebook.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.boris.psychologist.notebook.api.service.tg.command.BotCommandHandler;
import ru.boris.psychologist.notebook.dto.tg.command.BotCommands;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Configuration
public class BotCommandHandlerConfig {

    @Bean
    public Map<BotCommands, BotCommandHandler> botCommandHandlers(List<BotCommandHandler> handlers) {
        return handlers.stream()
                .collect(Collectors.toMap(BotCommandHandler::getCommand, Function.identity()));
    }
}
