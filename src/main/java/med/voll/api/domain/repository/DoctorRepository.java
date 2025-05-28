package med.voll.api.domain.repository;

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
            d.active = TRUE
            AND
            d.specialty = :specialty
            AND
            d.id NOT IN(
                SELECT c.doctor.id FROM Consultation c
                WHERE
                c.dateConsultation = :dateConsultation
            )
            ORDER BY FUNCTION('RANDOM')
            LIMIT 1
            """)
    Doctor chooseRandomDoctor(Specialty specialty, LocalDateTime dateConsultation);

    @Query("""
            SELECT d.active
            FROM Doctor d
            WHERE
            d.id = :id
            """)
    Boolean findActiveById(Long id);
}
