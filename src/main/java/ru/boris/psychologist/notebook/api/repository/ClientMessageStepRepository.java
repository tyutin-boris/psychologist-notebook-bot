package ru.boris.psychologist.notebook.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.boris.psychologist.notebook.entity.step.ClientMessageStepEntity;

import java.util.Optional;

@Repository
public interface ClientMessageStepRepository extends JpaRepository<ClientMessageStepEntity, Long> {

    Optional<ClientMessageStepEntity> findFirstByClientIdOrderByCreateDateTimeDesc(
            @Param("clientId") Long clientId);
}
