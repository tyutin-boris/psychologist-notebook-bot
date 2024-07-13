package ru.boris.psychologist.notebook.mapper;

public interface DtoToEntity<FROM, TO> {

    TO toDto(FROM entity);
}
