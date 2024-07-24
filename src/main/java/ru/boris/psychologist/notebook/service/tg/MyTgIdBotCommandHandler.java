package ru.boris.psychologist.notebook.service.tg;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.boris.psychologist.notebook.api.service.tg.command.BotCommandHandler;
import ru.boris.psychologist.notebook.dto.tg.ChatDto;
import ru.boris.psychologist.notebook.dto.tg.MessageDto;
import ru.boris.psychologist.notebook.dto.tg.ResponseDto;
import ru.boris.psychologist.notebook.dto.tg.UpdateDto;
import ru.boris.psychologist.notebook.dto.tg.UserDto;
import ru.boris.psychologist.notebook.dto.tg.command.BotCommands;

import java.util.Objects;
import java.util.Optional;

/**
 * Реализация сервиса для.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MyTgIdBotCommandHandler implements BotCommandHandler {

    @Override
    public Optional<ResponseDto> handle(UpdateDto dto) {
        Integer updateId = dto.getUpdateId();
        log.debug("Обработчик команды /tg_id, начал обрабатывать событие с id: " + updateId);

        Optional<MessageDto> message = Optional.ofNullable(dto.getMessage());

        Long chatId = message.map(MessageDto::getChat)
                .map(ChatDto::getId)
                .orElseThrow(() -> new RuntimeException(
                        String.format("Не удалось определить идентификатор чата. messageId: %s", updateId)));

        String userId = message.map(MessageDto::getFrom)
                .map(UserDto::getId)
                .map(Objects::toString)
                .orElse("Идентификатор не найден.");

        ResponseDto response = new ResponseDto();
        response.setChatId(chatId);
        response.setText(userId);

        return Optional.of(response);
    }

    @Override
    public BotCommands getCommand() {
        return BotCommands.MY_TG_ID;
    }
}
