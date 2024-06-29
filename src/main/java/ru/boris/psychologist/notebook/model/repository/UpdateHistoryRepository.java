package ru.boris.psychologist.notebook.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.boris.psychologist.notebook.model.UpdateHistoryEntity;

@Repository
public interface UpdateHistoryRepository extends JpaRepository<UpdateHistoryEntity, Long> {

}
