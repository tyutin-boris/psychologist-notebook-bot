package ru.boris.psychologist.notebook.dto.tg;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Getter
@RequiredArgsConstructor
public enum ChatType {
    PRIVATE("private"),
    GROUP("group"),
    CHANNEL("channel"),
    SUPERGROUP("supergroup"),
    INDEFINITELY("indefinitely");

    private final String name;

    public static ChatType stringToChatType(String chatType) {
        String type = Optional.ofNullable(chatType)
                .orElseThrow(() -> new RuntimeException("Type of chat is null, casting failed"));

        return switch (type) {
            case "private" -> ChatType.PRIVATE;
            case "group" -> ChatType.GROUP;
            case "channel" -> ChatType.CHANNEL;
            case "supergroup" -> ChatType.SUPERGROUP;
            default -> ChatType.INDEFINITELY;
        };
    }

}
