package ru.boris.psychologist.notebook.service.command;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.boris.psychologist.notebook.api.service.command.BotCommandHandler;
import ru.boris.psychologist.notebook.dto.ChatDto;
import ru.boris.psychologist.notebook.dto.MessageDto;
import ru.boris.psychologist.notebook.dto.ResponseDto;
import ru.boris.psychologist.notebook.dto.UpdateDto;
import ru.boris.psychologist.notebook.dto.command.BotCommands;

import java.util.Optional;

/**
 * Реализация сервиса для обработки команды /not_defined.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class NotDefinedBotCommandHandler implements BotCommandHandler {

    @Override
    public Optional<ResponseDto> handle(UpdateDto dto) {
        Integer updateId = dto.getUpdateId();
        log.debug("Обработчик команды /not_defined, начал обрабатывать событие с id: " + updateId);

        Long chatId = Optional.ofNullable(dto.getMessage())
                .map(MessageDto::getChat)
                .map(ChatDto::getId)
                .orElseThrow(() -> new RuntimeException(
                        String.format("Не удалось определить идентификатор чата. messageId: %s", updateId)));

        ResponseDto response = new ResponseDto();
        response.setChatId(chatId);
        response.setText("Я не умею выполнять такую команду. Попробуйте выбрать команду из Меню с лева" +
                " от поля ввода текста.");

        log.debug("Обработчик команды /not_defined, закончил обрабатывать событие с id: " + updateId);
        return Optional.of(response);
    }

    @Override
    public BotCommands getCommand() {
        return BotCommands.COMMAND_NOT_DEFINED;
    }
}
