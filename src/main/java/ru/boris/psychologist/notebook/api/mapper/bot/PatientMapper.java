package ru.boris.psychologist.notebook.api.mapper.bot;

import org.mapstruct.Mapper;
import ru.boris.psychologist.notebook.dto.bot.PatientDto;
import ru.boris.psychologist.notebook.api.mapper.DtoToEntity;
import ru.boris.psychologist.notebook.api.mapper.EntityToDto;
import ru.boris.psychologist.notebook.model.entity.PatientEntity;

@Mapper(componentModel = "spring")
public interface PatientMapper extends DtoToEntity<PatientEntity, PatientDto>,
        EntityToDto<PatientDto, PatientEntity> {
}
