package ru.boris.psychologist.notebook.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.boris.psychologist.notebook.api.service.tg.DefaultResponseService;
import ru.boris.psychologist.notebook.api.service.tg.command.BotCommandHandler;
import ru.boris.psychologist.notebook.dto.tg.UpdateDto;
import ru.boris.psychologist.notebook.dto.tg.command.BotCommands;
import ru.boris.psychologist.notebook.service.tg.UpdateHandlerImpl;
import ru.boris.psychologist.notebook.service.tg.command.NotDefinedBotCommandHandler;
import ru.boris.psychologist.notebook.service.tg.command.StartBotCommandHandler;

import java.util.Map;

import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class UpdateHandlerImplTest {

    @Mock
    private DefaultResponseService defaultResponseService;

    @Mock
    private Map<BotCommands, BotCommandHandler> botCommandHandlers;

    @InjectMocks
    private UpdateHandlerImpl sut;

    @BeforeEach
    void setUp() {
        StartBotCommandHandler startBotCommandHandler = mock(StartBotCommandHandler.class);
        NotDefinedBotCommandHandler notDefinedBotCommandHandler = mock(NotDefinedBotCommandHandler.class);

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
        sut.handle(new UpdateDto());

        // Проверка

    }
}
