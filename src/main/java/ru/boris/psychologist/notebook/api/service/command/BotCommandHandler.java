package ru.boris.psychologist.notebook.api.service.command;

import ru.boris.psychologist.notebook.dto.tg.ResponseDto;
import ru.boris.psychologist.notebook.dto.tg.UpdateDto;
import ru.boris.psychologist.notebook.dto.tg.command.BotCommands;

import java.util.Optional;

/**
 * Сервис для обработки различных команд от пользователя.
 */
public interface BotCommandHandler {


    /**
     * Метод обработки команды.
     *
     * @param dto событие полученное от пользователя.
     * @return ответ пользователю.
     */
    Optional<ResponseDto> handle(UpdateDto dto);

    /**
     * Метод для получения тепа обрабатываемой команды.
     *
     * @return команда, которую обрабатывает сервис
     */
    BotCommands getCommand();
}
