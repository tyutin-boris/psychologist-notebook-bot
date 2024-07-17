package ru.boris.psychologist.notebook.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.boris.psychologist.notebook.model.entity.PatientMessageHistoryEntity;

@Repository
public interface PatientMessageHistoryRepository extends JpaRepository<PatientMessageHistoryEntity, Long> {
}
