package ru.boris.psychologist.notebook.api.service.domain.client;

import ru.boris.psychologist.notebook.dto.domain.ClientDto;

public interface ClientService {

    void saveIfNotExist(ClientDto dto);

    boolean savePhoneNumber(String phoneNumber, ClientDto dto);

    boolean saveDescription(String description, ClientDto dto);

    void saveNameToContact(Long id, String clientName);
}
