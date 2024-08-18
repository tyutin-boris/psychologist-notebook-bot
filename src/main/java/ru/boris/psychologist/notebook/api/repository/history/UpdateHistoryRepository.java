package ru.boris.psychologist.notebook.api.repository.history;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.boris.psychologist.notebook.entity.history.UpdateHistoryEntity;

@Repository
public interface UpdateHistoryRepository extends JpaRepository<UpdateHistoryEntity, Long> {

}
