package ru.boris.psychologist.notebook.mapper;

import org.assertj.core.api.Assertions;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import ru.boris.psychologist.notebook.dto.tg.ChatDto;
import ru.boris.psychologist.notebook.dto.tg.ChatType;
import ru.boris.psychologist.notebook.dto.tg.MessageDto;
import ru.boris.psychologist.notebook.dto.tg.UpdateDto;
import ru.boris.psychologist.notebook.dto.tg.UserDto;
import ru.boris.psychologist.notebook.mapper.tg.UpdateDtoMapper;

@SpringJUnitConfig(classes = {
        UpdateDtoMapperImpl.class,
        MessageDtoMapperImpl.class,
        UserDtoMapperImpl.class,
        ChatDtoMapperImpl.class,
        MessageEntityDtoMapperImpl.class})
class UpdateDtoMapperTest {

    @Autowired
    private UpdateDtoMapper sut;

    @Test
    public void should_returnDto() {
        // Подготовка
        Update update = getUpdate();
        UpdateDto expected = getExpected();

        // Действие
        UpdateDto actual = sut.toDto(update);

        // Проверка
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    private Update getUpdate() {
        Message message = getMessage();

        Update update = new Update();
        update.setMessage(message);

        return update;
    }

    private Message getMessage() {
        Chat chat = getChat();
        User from = getFrom();

        Message message = new Message();
        message.setMessageId(18);
        message.setChat(chat);
        message.setFrom(from);
        message.setText("/start");

        return message;
    }

    @NotNull
    private User getFrom() {
        User user = new User();

        user.setId(585815794L);
        user.setFirstName("Boris");
        user.setLastName("Tyutin");
        user.setUserName("byutin");

        return user;
    }

    @NotNull
    private Chat getChat() {
        Chat chat = new Chat();

        chat.setId(585815794L);
        chat.setFirstName("Boris");
        chat.setLastName("Tyutin");
        chat.setUserName("btutin");
        chat.setType("private");

        return chat;
    }

    private UpdateDto getExpected() {
        MessageDto messageDto = getMessageDto();

        UpdateDto dto = new UpdateDto();
        dto.setMessage(messageDto);

        return dto;
    }

    private MessageDto getMessageDto() {
        UserDto from = getFromDto();
        ChatDto chat = getChatDto();

        MessageDto dto = new MessageDto();
        dto.setMessageId(18);
        dto.setChat(chat);
        dto.setFrom(from);

        return dto;
    }

    private ChatDto getChatDto() {
        ChatDto dto = new ChatDto();

        dto.setId(585815794L);
        dto.setFirstName("Boris");
        dto.setLastName("Tyutin");
        dto.setUserName("btutin");
        dto.setType(ChatType.PRIVATE);

        return dto;
    }

    private UserDto getFromDto() {
        UserDto from = new UserDto();

        from.setId(585815794L);
        from.setFirstName("Boris");
        from.setLastName("Tyutin");
        from.setUserName("byutin");

        return from;
    }
}
