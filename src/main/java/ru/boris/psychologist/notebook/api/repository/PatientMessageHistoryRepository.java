package ru.boris.psychologist.notebook.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.boris.psychologist.notebook.model.entity.PatientMessageHistoryEntity;

import java.util.Optional;

@Repository
public interface PatientMessageHistoryRepository extends JpaRepository<PatientMessageHistoryEntity, Long> {

    Optional<PatientMessageHistoryEntity> findFirstByPatientIdOrderByCreateDateTimeDesc(
            @Param("patientId") Long patientId);
}
