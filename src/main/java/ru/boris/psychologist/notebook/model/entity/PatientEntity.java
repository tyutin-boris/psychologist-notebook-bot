package ru.boris.psychologist.notebook.model.entity;

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
@Entity(name = "patient")
@AllArgsConstructor
@NoArgsConstructor
public class PatientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "tg_id", nullable = false, unique = true)
    private Long tgId;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "description", length = 6144)
    private String description;

    @Column(name = "create_date_time", nullable = false)
    private OffsetDateTime createDateTime = OffsetDateTime.now();

    @Column(name = "update_date_time")
    private OffsetDateTime updateDateTime;

    @Column(name = "name_to_contact", length = 1024)
    private String nameToContact;
}
