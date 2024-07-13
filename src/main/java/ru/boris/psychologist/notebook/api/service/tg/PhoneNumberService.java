package ru.boris.psychologist.notebook.api.service.tg;

import ru.boris.psychologist.notebook.dto.tg.UpdateDto;

public interface PhoneNumberService {
    void saveNumber(UpdateDto updateDto);
}
