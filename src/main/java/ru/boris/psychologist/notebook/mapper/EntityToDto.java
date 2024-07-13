package ru.boris.psychologist.notebook.mapper;

public interface EntityToDto<FROM, TO> {

    TO toEntity(FROM dto);
}
