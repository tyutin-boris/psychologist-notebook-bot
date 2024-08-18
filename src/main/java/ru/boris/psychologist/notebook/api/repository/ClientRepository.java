package ru.boris.psychologist.notebook.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.boris.psychologist.notebook.entity.ClientEntity;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
    boolean existsByUsernameOrTgId(String username, Long tgId);
}
