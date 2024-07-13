package ru.boris.psychologist.notebook.api.service;

import ru.boris.psychologist.notebook.dto.tg.UpdateDto;

public interface PhoneNumberService {
    void saveNumber(UpdateDto updateDto);
}
