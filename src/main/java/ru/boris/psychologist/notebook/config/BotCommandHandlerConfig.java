package ru.boris.psychologist.notebook.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.boris.psychologist.notebook.api.service.tg.command.TgBotCommandHandler;
import ru.boris.psychologist.notebook.dto.domain.command.BotCommands;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Configuration
public class BotCommandHandlerConfig {

    @Bean
    public Map<BotCommands, TgBotCommandHandler> botCommandHandlers(List<TgBotCommandHandler> handlers) {
        return handlers.stream()
                .collect(Collectors.toMap(TgBotCommandHandler::getCommand, Function.identity()));
    }
}
