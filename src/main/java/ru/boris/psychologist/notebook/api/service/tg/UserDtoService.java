package ru.boris.psychologist.notebook.api.service.tg;

import ru.boris.psychologist.notebook.dto.tg.UpdateDto;

import java.util.Optional;

/**
 * Сервис для работы с UserDto.
 */
public interface UserDtoService {

    Optional<Long> getUserId(UpdateDto dto);

    Optional<Long> getUserIdFromCallBack(UpdateDto dto);
}
