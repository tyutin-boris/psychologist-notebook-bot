package ru.boris.psychologist.notebook.entity.step;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.boris.psychologist.notebook.dto.tg.step.ClientMessageStepType;

import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "client_message_step")
@NoArgsConstructor
@AllArgsConstructor
public class ClientMessageStepEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "client_id", nullable = false)
    private Long clientId;

    @Enumerated(EnumType.STRING)
    @Column(name = "step", nullable = false)
    private ClientMessageStepType step;

    @Column(name = "create_date_time", nullable = false)
    private OffsetDateTime createDateTime = OffsetDateTime.now();
}
