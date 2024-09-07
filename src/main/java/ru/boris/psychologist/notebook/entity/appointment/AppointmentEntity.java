package ru.boris.psychologist.notebook.entity.appointment;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Table
@Getter
@Setter
@Entity(name = "appointment")
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "tg_id", nullable = false, unique = true)
    private Long tgId;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "question", length = 6144)
    private String question;

    @Column(name = "possible_time", length = 1024)
    private String possibleTime;

    @Column(name = "name_to_contact", length = 1024)
    private String nameToContact;

    @Column(name = "create_date_time", nullable = false)
    private OffsetDateTime createDateTime = OffsetDateTime.now();

    @Column(name = "update_date_time")
    private OffsetDateTime updateDateTime;
}
