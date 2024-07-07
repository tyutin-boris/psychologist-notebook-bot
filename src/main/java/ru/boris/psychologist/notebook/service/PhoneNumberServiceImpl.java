package ru.boris.psychologist.notebook.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.boris.psychologist.notebook.api.service.PhoneNumberService;
import ru.boris.psychologist.notebook.dto.UpdateDto;

/**
 * Реализация сервиса для.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PhoneNumberServiceImpl implements PhoneNumberService {

    @Override
    public void saveNumber(UpdateDto updateDto) {
        Integer updateId = updateDto.getUpdateId();
        log.debug("Старт сохранения номера телефона. id: {}", updateId);



        log.debug("Конец сохранения номера телефона. id: {}", updateId);
    }
}
