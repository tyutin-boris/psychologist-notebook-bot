package ru.boris.psychologist.notebook.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BotCommands {

    START("/start", "Начало диалога");

    private final String command;
    private final String description;

}
