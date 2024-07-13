package ru.boris.psychologist.notebook.api.mapper;

public interface EntityToDto<FROM, TO> {

    TO toEntity(FROM dto);
}
