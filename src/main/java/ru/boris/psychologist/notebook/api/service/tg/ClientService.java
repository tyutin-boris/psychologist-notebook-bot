package ru.boris.psychologist.notebook.api.service.tg;

import ru.boris.psychologist.notebook.dto.bot.ClientDto;

public interface ClientService {

    void saveIfNotExist(ClientDto dto);

    boolean savePhoneNumber(String phoneNumber, ClientDto dto);

    boolean saveDescription(String description, ClientDto dto);

    void saveNameToContact(Long id, String clientName);
}
