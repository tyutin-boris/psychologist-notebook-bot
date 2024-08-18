package ru.boris.psychologist.notebook.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.boris.psychologist.notebook.api.service.tg.response.DefaultResponseService;
import ru.boris.psychologist.notebook.api.service.tg.command.TgBotCommandHandler;
import ru.boris.psychologist.notebook.dto.tg.UpdateDto;
import ru.boris.psychologist.notebook.dto.domain.command.BotCommands;
import ru.boris.psychologist.notebook.service.tg.UpdateHandlerImpl;
import ru.boris.psychologist.notebook.service.tg.command.NotDefinedTgBotCommandHandler;
import ru.boris.psychologist.notebook.service.tg.command.StartTgBotCommandHandler;

import java.util.Map;

import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class UpdateHandlerImplTest {

    @Mock
    private DefaultResponseService defaultResponseService;

    @Mock
    private Map<BotCommands, TgBotCommandHandler> botCommandHandlers;

    @InjectMocks
    private UpdateHandlerImpl sut;

    @BeforeEach
    void setUp() {
        StartTgBotCommandHandler startBotCommandHandler = mock(StartTgBotCommandHandler.class);
        NotDefinedTgBotCommandHandler notDefinedBotCommandHandler = mock(NotDefinedTgBotCommandHandler.class);

//        when(botCommandHandlers.get(BotCommands.START))
//                .thenReturn(startBotCommandHandler);
//
//        when(botCommandHandlers.get(BotCommands.COMMAND_NOT_DEFINED))
//                .thenReturn(notDefinedBotCommandHandler);
    }

    @Test
    @DisplayName("")
    public void should_throwException_when() {
        // Подготовка


        // Действие
//        sut.handle(new UpdateDto());

        // Проверка

    }
}
