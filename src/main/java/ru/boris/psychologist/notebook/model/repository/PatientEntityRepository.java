package ru.boris.psychologist.notebook.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.boris.psychologist.notebook.model.entity.PatientEntity;

@Repository
public interface PatientEntityRepository extends JpaRepository<PatientEntity, Long> {
}
