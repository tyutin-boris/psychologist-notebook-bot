package ru.boris.psychologist.notebook.service.tg;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import ru.boris.psychologist.notebook.api.service.tg.ResponseService;
import ru.boris.psychologist.notebook.dto.tg.ChatDto;
import ru.boris.psychologist.notebook.dto.tg.MessageDto;
import ru.boris.psychologist.notebook.dto.tg.ReplyKeyboardDto;
import ru.boris.psychologist.notebook.dto.tg.ResponseDto;
import ru.boris.psychologist.notebook.dto.tg.UpdateDto;
import ru.boris.psychologist.notebook.dto.tg.UserDto;
import ru.boris.psychologist.notebook.dto.tg.command.BotCommands;
import ru.boris.psychologist.notebook.service.tg.keyboard.ReplyKeyboardService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Реализация сервиса для формирования ответов.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ResponseServiceImpl implements ResponseService {

    private final ReplyKeyboardService replyKeyboardService;

    @Override
    public Optional<ResponseDto> getStartCommandResponse(UpdateDto dto) {
        Optional<Long> chatIdOpt = getChatIdOpt(dto);

        if (chatIdOpt.isEmpty()) {
            log.error("Не удалось сформировать сообщение для команды {}, нет идентификатора чата",
                    BotCommands.START.getCommand());
            return Optional.empty();
        }

        ReplyKeyboardDto addPhoneNumber = replyKeyboardService.getKeyboardForAddPhoneNumber();
        ReplyKeyboardDto addDescription = replyKeyboardService.getKeyboardForAddDescription();
        ReplyKeyboardDto makeAnAppointment = replyKeyboardService.getKeyboardForMakeAnAppointment();

        String firstName = getFirstName(dto.getMessage());

        ResponseDto response = new ResponseDto();
        response.setChatId(chatIdOpt.get());
        response.setText(getHelloMessage(firstName));
        response.setReplyMarkup(List.of(addPhoneNumber, addDescription, makeAnAppointment));

        return Optional.of(response);
    }

    @Override
    public Optional<ResponseDto> getMyTgIdCommandResponse(UpdateDto dto) {
        String userId = Optional.ofNullable(dto.getMessage())
                .map(MessageDto::getFrom)
                .map(UserDto::getId)
                .map(Objects::toString)
                .orElse("Идентификатор не найден.");

        Optional<Long> chatIdOpt = getChatIdOpt(dto);

        if (chatIdOpt.isEmpty()) {
            log.error("Не удалось сформировать сообщение для команды {}, нет идентификатора чата",
                    BotCommands.MY_TG_ID.getCommand());
            return Optional.empty();
        }

        ResponseDto response = new ResponseDto();
        response.setChatId(chatIdOpt.get());
        response.setText(userId);

        return Optional.of(response);
    }

    @Override
    public Optional<ResponseDto> getNotDefinedComandResponse(UpdateDto dto) {
        Optional<Long> chatIdOpt = getChatIdOpt(dto);

        if (chatIdOpt.isEmpty()) {
            log.error("Не удалось сформировать сообщение для команды {}, нет идентификатора чата",
                    BotCommands.COMMAND_NOT_DEFINED.getCommand());
            return Optional.empty();
        }

        ResponseDto response = new ResponseDto();
        response.setChatId(chatIdOpt.get());
        response.setText("Я не умею выполнять такую команду. Попробуйте выбрать команду из Меню с лева" +
                " от поля ввода текста.");
        return Optional.of(response);
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

    private String getHelloMessage(String firstName) {
        return "Здравствуй," + firstName + ". Я бот-помощник семейных психологов \n" +
                "Сергея и Ольги Тютиных. Выбери, что тебя интересует.";
    }

    private Optional<Long> getChatIdOpt(UpdateDto dto) {
        return Optional.ofNullable(dto.getMessage())
                .map(MessageDto::getChat)
                .map(ChatDto::getId);
    }
}
