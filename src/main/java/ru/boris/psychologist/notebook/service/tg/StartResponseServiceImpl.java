package ru.boris.psychologist.notebook.service.tg;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import ru.boris.psychologist.notebook.api.service.tg.StartResponseService;
import ru.boris.psychologist.notebook.dto.tg.MessageDto;
import ru.boris.psychologist.notebook.dto.tg.ReplyKeyboardDto;
import ru.boris.psychologist.notebook.dto.tg.ResponseDto;
import ru.boris.psychologist.notebook.dto.tg.UserDto;

import java.util.List;
import java.util.Optional;

/**
 * Реализация сервиса для.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class StartResponseServiceImpl implements StartResponseService {

    @Override
    public ResponseDto getStartResponseDto(MessageDto messageDto, Long chatId) {
        ReplyKeyboardDto addPhoneNumber = getKeyboardForAddPhoneNumber();
        ReplyKeyboardDto addDescription = getKeyboardForAddDescription();
        ReplyKeyboardDto makeAnAppointment = getKeyboardForMakeAnAppointment();

        ResponseDto response = new ResponseDto();
        response.setChatId(chatId);
        response.setText(getHelloMessage(getFirstName(messageDto)));
        response.setReplyMarkup(List.of(addPhoneNumber, addDescription, makeAnAppointment));
        return response;
    }

    private String getFirstName(MessageDto messageDto) {
        String firstName = Optional.ofNullable(messageDto)
                .map(MessageDto::getFrom)
                .map(UserDto::getFirstName)
                .orElse(StringUtils.SPACE);

        if (StringUtils.isNotBlank(firstName)) {
            return StringUtils.SPACE + firstName;
        }

        return firstName;
    }

    private ReplyKeyboardDto getKeyboardForAddPhoneNumber() {
        ReplyKeyboardDto replyMarkup = new ReplyKeyboardDto();
        replyMarkup.setText("Добавить номер телефона.");
        replyMarkup.setCallbackData("add_phone_number");
        return replyMarkup;
    }

    private ReplyKeyboardDto getKeyboardForAddDescription() {
        ReplyKeyboardDto replyMarkup = new ReplyKeyboardDto();
        replyMarkup.setText("Описать проблему.");
        replyMarkup.setCallbackData("add_description");
        return replyMarkup;
    }

    private ReplyKeyboardDto getKeyboardForMakeAnAppointment() {
        ReplyKeyboardDto replyMarkup = new ReplyKeyboardDto();
        replyMarkup.setText("Записаться на приём");
        replyMarkup.setCallbackData("make_an_appointment");
        return replyMarkup;
    }

    private String getHelloMessage(String firstName) {
        return "Здравствуй," + firstName + ". Я бот-помощник семейных психологов \n" +
                "Сергея и Ольги Тютиных. Выбери, что тебя интересует.";
    }
}
