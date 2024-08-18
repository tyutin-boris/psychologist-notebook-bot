package ru.boris.psychologist.notebook.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.boris.psychologist.notebook.api.service.tg.message.MessageHandler;
import ru.boris.psychologist.notebook.dto.domain.step.ClientMessageStepType;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Configuration
public class MessageHandlersConfig {

    @Bean
    public Map<ClientMessageStepType, MessageHandler> messageHandlers(List<MessageHandler> handlers) {
        return handlers.stream()
                .collect(Collectors.toMap(MessageHandler::getStepType, Function.identity()));
    }
}
