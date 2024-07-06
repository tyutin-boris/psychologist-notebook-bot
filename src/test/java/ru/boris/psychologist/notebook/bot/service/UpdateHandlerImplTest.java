package ru.boris.psychologist.notebook.bot.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.boris.psychologist.notebook.service.UpdateHandlerImpl;

@ExtendWith(MockitoExtension.class)
class UpdateHandlerImplTest {

    @InjectMocks
    private UpdateHandlerImpl sut;

    @Test
    public void should_throwException_when_messageIsNull() {
        // Подготовка
        Update update = new Update();
        update.setMessage(null);

        String expected = "Отсутствует message";

        // Действие
//        Executable executable = () -> sut.handle(update);

        // Проверка
//        MessageHandlerException exception = Assertions.assertThrows(MessageHandlerException.class, executable);
//        Assertions.assertEquals(expected, exception.getMessage());

    }

    @Test
    public void should_throwException_when_chatIdIsNull() {
        // Подготовка
        Message message = new Message();
        message.setChat(null);

        Update update = new Update();
        update.setMessage(message);

        String expected = "Отсутствует chatId";

        // Действие
//        Executable executable = () -> sut.handle(update);

        // Проверка
//        MessageHandlerException exception = Assertions.assertThrows(MessageHandlerException.class, executable);
//        Assertions.assertEquals(expected, exception.getMessage());
    }

    @Test
    public void should_returnNewMessage() {
        // Подготовка
        long chatId = 1L;
        Update update = getUpdate(chatId);

        SendMessage expected = new SendMessage();
        expected.setChatId(chatId);
        expected.setText("Hello");

        // Действие
//        SendMessage actual = sut.handle(update);

        // Проверка
//        Assertions.assertEquals(expected, actual);
    }

    private Update getUpdate(long chatId) {
        Chat chat = new Chat();
        chat.setId(chatId);

        Message message = new Message();
        message.setChat(chat);

        Update update = new Update();
        update.setMessage(message);
        return update;
    }
}
