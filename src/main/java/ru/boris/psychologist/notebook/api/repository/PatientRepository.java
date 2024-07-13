package ru.boris.psychologist.notebook.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.boris.psychologist.notebook.model.entity.PatientEntity;

import java.time.OffsetDateTime;

@Repository
public interface PatientRepository extends JpaRepository<PatientEntity, Long> {


    boolean existsByUsernameOrTgId(String username, Long tgId);

    @Modifying
    @Query(value = "update patient p " +
            "set p.phoneNumber = :phoneNumber, " +
            "p.updateDateTime = :updateDateTime " +
            "where p.username = :username " +
            "and p.tgId = :tgId")
    void updatePhoneNumberByUsernameAndTgId(@Param("phoneNumber") String phoneNumber,
                                            @Param("updateDateTime") OffsetDateTime updateDateTime,
                                            @Param("username") String username,
                                            @Param("tgId") Long tgId);
}
