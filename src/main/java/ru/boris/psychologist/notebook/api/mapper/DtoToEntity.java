package ru.boris.psychologist.notebook.api.mapper;

public interface DtoToEntity<FROM, TO> {

    TO toDto(FROM entity);
}
