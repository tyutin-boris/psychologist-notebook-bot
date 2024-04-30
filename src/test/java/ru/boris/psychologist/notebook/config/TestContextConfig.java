package ru.boris.psychologist.notebook.config;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import ru.boris.psychologist.notebook.bot.BotInitializer;

@Configuration
@ComponentScan(basePackages = "ru.boris.psychologist.notebook",
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = BotInitializer.class)})
public class TestContextConfig {

    @Bean
    public BotInitializer botInitializer() {
        return Mockito.mock(BotInitializer.class);
    }
}
