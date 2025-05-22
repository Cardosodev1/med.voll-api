package med.voll.api.domain.repository;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.entity.doctor.Doctor;
import med.voll.api.domain.entity.doctor.Specialty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    Page<Doctor> findAllByActiveTrue(Pageable pageable);

    @Query("""
            SELECT d FROM Doctor d
            WHERE
            d.active = 1
            AND
            d.specialty = :specialty
            AND
            d.id NOT IN(
                SELECT c.doctor.id FROM Consultation c
                WHERE
                c.dateConsultation = :dateConsultation
            )
            ORDER BY RAND()
            LIMIT 1
            """)
    Doctor chooseRandomDoctor(Specialty specialty, LocalDateTime dateConsultation);
}
