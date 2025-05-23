package med.voll.api.domain.entity.consultation;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.entity.doctor.Doctor;
import med.voll.api.domain.entity.patient.Patient;
import med.voll.api.domain.validations.cancellation.CancellationReason;

import java.time.LocalDateTime;

@Entity
@Table(name = "consultations")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Consultation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @JoinColumn(name = "date_consultation")
    private LocalDateTime dateConsultation;

    @Column(name = "cancellation_reason")
    @Enumerated(EnumType.STRING)
    private CancellationReason cancellationReason;

    public void cancel(CancellationReason reason) {
        this.cancellationReason = reason;
    }

}
