package ru.boris.psychologist.notebook.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.boris.psychologist.notebook.api.service.tg.callback.TgCallbackQueryHandlers;
import ru.boris.psychologist.notebook.dto.domain.callback.CallbackTypes;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Configuration
public class CallbackQueryHandlersConfig {

    @Bean
    public Map<CallbackTypes, TgCallbackQueryHandlers> callbackQueryHandlers(List<TgCallbackQueryHandlers> handlers) {
        return handlers.stream()
                .collect(Collectors.toMap(TgCallbackQueryHandlers::getCallbackType, Function.identity()));
    }
}
