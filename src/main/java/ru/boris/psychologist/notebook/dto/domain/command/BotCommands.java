package ru.boris.psychologist.notebook.dto.domain.command;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@RequiredArgsConstructor
public enum BotCommands {

    START("/start", "Начало диалога"),
    MY_TG_ID("/tg_id", "Получить свой идентификатор в тг"),
    COMMAND_NOT_DEFINED("/not_defined", "Тип команды не определен");

    private final String command;
    private final String description;


    public static BotCommands getBotCommands(String command) {
        log.debug("Определение типа команды: {}", command);

        if (command == null) {
            log.error("Попытка определить команду для null, возвращён неопределенный тип.");
            return COMMAND_NOT_DEFINED;
        }

        return switch (command) {
            case "/start" -> START;
            case "/tg_id" -> MY_TG_ID;
            default -> COMMAND_NOT_DEFINED;
        };
    }
}
