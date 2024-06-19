package ru.boris.psychologist.notebook.mapper;

/**
 * Интерфейс для маппинга одного класса в другой.
 *
 * @param <FROM> исходный класс
 * @param <TO> результат
 */
public interface DtoToDto<FROM, TO>{

    /**
     * Метод преобразования одного Dto в другое Dto.
     *
     * @param from исходное Dto
     * @return результат
     */
    TO toDto(FROM from);
}
