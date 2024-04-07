package ru.boris.psychologist.notebook.bot;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.boris.psychologist.notebook.bot.exception.SendMessageException;
import ru.boris.psychologist.notebook.bot.service.api.UpdateHundler;
import ru.boris.psychologist.notebook.config.BotConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TelegramBotTest {

    private final long chatId = 123L;

    @Mock
    private BotConfig botConfig;

    @Mock
    private UpdateHundler updateHundler;

    @InjectMocks
    private TelegramBot sut;

    @Test
    @Disabled("Нет возможности за мокать http client")
    public void should_sendMessage() throws TelegramApiException {
        // Подготовка
        Update update = getUpdate();

        SendMessage newMessage = new SendMessage();
        newMessage.setChatId(chatId);
        newMessage.setText("test");

        when(updateHundler.handle(update))
                .thenReturn(newMessage);

        // Действие
        sut.onUpdateReceived(update);

        // Проверка
        verify(sut).execute(newMessage);
    }

    @Test
    public void should_throwException_when_updateIsNull() {
        // Подготовка
        String expected = "В сообщении отсутствует message";

        // Действие
        Executable executable = () -> sut.onUpdateReceived(new Update());

        // Проверка
        SendMessageException exc = assertThrows(SendMessageException.class, executable);
        assertEquals(expected, exc.getMessage());
    }

    private Update getUpdate() {
        Update update = new Update();
        Message message = new Message();
        Chat chat = new Chat();

        chat.setId(chatId);
        message.setChat(chat);
        update.setMessage(message);

        return update;
    }
}
