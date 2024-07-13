package ru.boris.psychologist.notebook.api.mapper;

import java.util.List;

public interface DtoListListToDtoListList<FROM, TO> {

    List<List<TO>> toDtoList(List<List<FROM>> froms);
}
