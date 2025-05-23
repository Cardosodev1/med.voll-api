package med.voll.api.domain.repository;

import med.voll.api.domain.entity.patient.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    Page<Patient> findAllByActiveTrue(Pageable pageable);

    @Query("""
            SELECT p.active
            FROM Patient p
            WHERE
            p.id = :id
            """)
    Boolean findActiveById(Long id);
}
