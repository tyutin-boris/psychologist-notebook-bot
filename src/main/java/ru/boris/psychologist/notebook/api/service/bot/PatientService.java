package ru.boris.psychologist.notebook.api.service.bot;

import ru.boris.psychologist.notebook.dto.bot.PatientDto;

public interface PatientService {

    void saveIfNotExist(PatientDto dto);

    boolean savePhoneNumber(String phoneNumber, PatientDto dto);
}
