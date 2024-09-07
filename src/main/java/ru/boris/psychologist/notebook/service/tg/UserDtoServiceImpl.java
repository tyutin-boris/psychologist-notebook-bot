package ru.boris.psychologist.notebook.service.tg;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.boris.psychologist.notebook.api.service.tg.UserDtoService;
import ru.boris.psychologist.notebook.dto.tg.CallbackQueryDto;
import ru.boris.psychologist.notebook.dto.tg.ChatDto;
import ru.boris.psychologist.notebook.dto.tg.MessageDto;
import ru.boris.psychologist.notebook.dto.tg.UpdateDto;
import ru.boris.psychologist.notebook.dto.tg.UserDto;

import java.util.Optional;

/**
 * Реализация сервиса для работы с UserDto.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserDtoServiceImpl implements UserDtoService {

    @Override
    public Optional<Long> getUserId(UpdateDto dto) {
        return Optional.ofNullable(dto.getMessage())
                .map(MessageDto::getFrom)
                .map(UserDto::getId);
    }

    @Override
    public Optional<Long> getUserIdFromCallBack(UpdateDto dto) {
        return Optional.ofNullable(dto.getCallbackQuery())
                .map(CallbackQueryDto::getMessage)
                .map(MessageDto::getChat)
                .map(ChatDto::getId);
    }
}
