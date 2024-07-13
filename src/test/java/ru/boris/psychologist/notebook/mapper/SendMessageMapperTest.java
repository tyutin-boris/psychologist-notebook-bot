package ru.boris.psychologist.notebook.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.boris.psychologist.notebook.api.mapper.tg.SendMessageMapperImpl;
import ru.boris.psychologist.notebook.dto.tg.ResponseDto;
import ru.boris.psychologist.notebook.api.mapper.tg.SendMessageMapper;

import static org.assertj.core.api.Assertions.assertThat;

@SpringJUnitConfig(classes = SendMessageMapperImpl.class)
class SendMessageMapperTest {

    private final String text = "text";
    private final Long chatId = 123L;

    @Autowired
    private SendMessageMapper sut;

    @Test
    public void should_returnDto() {
        // Подготовка
        SendMessage expected = getExpected();
        ResponseDto dto = getDto();

        // Действие
        SendMessage actual = sut.toDto(dto);

        // Проверка
        assertThat(actual).isEqualTo(expected);
    }

    private SendMessage getExpected() {
        SendMessage sendMessage = new SendMessage();

        sendMessage.setChatId(chatId);
        sendMessage.setText(text);

        return sendMessage;
    }

    private ResponseDto getDto() {
        ResponseDto dto = new ResponseDto();

        dto.setChatId(chatId);
        dto.setText(text);

        return dto;
    }
}
