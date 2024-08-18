package ru.boris.psychologist.notebook.service.tg;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.boris.psychologist.notebook.api.service.tg.ChatDtoService;
import ru.boris.psychologist.notebook.dto.tg.CallbackQueryDto;
import ru.boris.psychologist.notebook.dto.tg.ChatDto;
import ru.boris.psychologist.notebook.dto.tg.MessageDto;
import ru.boris.psychologist.notebook.dto.tg.UpdateDto;

import java.util.Optional;

/**
 * Реализация сервиса для работы с ChatDto.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ChatDtoServiceImpl implements ChatDtoService {

    @Override
    public Optional<Long> getChatIdOpt(UpdateDto dto) {
        return Optional.ofNullable(dto.getMessage())
                .map(MessageDto::getChat)
                .map(ChatDto::getId);
    }

    @Override
    public Optional<Long> getChatIdOptFromCallbackQueryDto(UpdateDto dto) {
        return Optional.of(dto)
                .map(UpdateDto::getCallbackQuery)
                .map(CallbackQueryDto::getMessage)
                .map(MessageDto::getChat)
                .map(ChatDto::getId);
    }
}
