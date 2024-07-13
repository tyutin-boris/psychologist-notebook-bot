package ru.boris.psychologist.notebook.mapper.bot;

import org.mapstruct.Mapper;
import ru.boris.psychologist.notebook.dto.bot.PatientDto;
import ru.boris.psychologist.notebook.mapper.DtoToEntity;
import ru.boris.psychologist.notebook.mapper.EntityToDto;
import ru.boris.psychologist.notebook.model.entity.PatientEntity;

@Mapper
public interface PatientDtoMapper extends DtoToEntity<PatientEntity, PatientDto>,
        EntityToDto<PatientDto, PatientEntity> {
}
