package ru.boris.psychologist.notebook.api.mapper.client;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.boris.psychologist.notebook.api.mapper.DtoToEntity;
import ru.boris.psychologist.notebook.api.mapper.EntityToDto;
import ru.boris.psychologist.notebook.dto.bot.ClientDto;
import ru.boris.psychologist.notebook.entity.ClientEntity;

@Mapper(componentModel = "spring")
public interface ClientMapper extends DtoToEntity<ClientEntity, ClientDto>,
        EntityToDto<ClientDto, ClientEntity> {

    @Override
    ClientDto toDto(ClientEntity entity);

    @Mapping(target = "updateDateTime", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createDateTime", ignore = true)
    @Override
    ClientEntity toEntity(ClientDto dto);
}
