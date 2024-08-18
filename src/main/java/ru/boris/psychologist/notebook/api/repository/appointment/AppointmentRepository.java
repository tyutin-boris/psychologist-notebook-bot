package ru.boris.psychologist.notebook.api.repository.appointment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.boris.psychologist.notebook.entity.appointment.AppointmentEntity;

@Repository
public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Long> {

    @Modifying
    @Query("update appointment a " +
            "set a.nameToContact = :nameToContact " +
            "where a.tgId = :clientId")
    void updateNameToContactByTgId(@Param("nameToContact") String nameToContact, @Param("clientId") Long clientId);


    @Modifying
    @Query("update appointment a " +
            "set a.phoneNumber = :phoneNumber " +
            "where a.tgId = :clientId")
    void updatePhoneNumberByTgId(@Param("phoneNumber") String phoneNumber, @Param("clientId") Long clientId);
}
