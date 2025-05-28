package med.voll.api.domain.repository;

import med.voll.api.domain.dto.address.AddressDTO;
import med.voll.api.domain.dto.doctor.DoctorDTO;
import med.voll.api.domain.dto.patient.PatientDTO;
import med.voll.api.domain.entity.consultation.Consultation;
import med.voll.api.domain.entity.doctor.Doctor;
import med.voll.api.domain.entity.doctor.Specialty;
import med.voll.api.domain.entity.patient.Patient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class DoctorRepositoryTest {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("Deveria devolver null quando único médico cadastrado não está disponível na data")
    void chooseRandomDoctorScenario1() {
        // given ou arrange
        var nextMondayAt10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);
        var doctor = registerDoctor("Médico", "medico@voll.med", "123456", Specialty.CARDIOLOGIA);
        var patient = registerPatient("Paciente", "paciente@email.com", "00000000000");
        registerConsultation(doctor, patient, nextMondayAt10);

        // when ou act
        var freeDoctor = doctorRepository.chooseRandomDoctor(Specialty.CARDIOLOGIA, nextMondayAt10);

        // then ou assert
        assertThat(freeDoctor).isNull();
    }

    @Test
    @DisplayName("Deveria devolver médico quando ele estiver disponível na data")
    void chooseRandomDoctorScenario2() {
        // given ou arrange
        var nextMondayAt10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);
        var doctor = registerDoctor("Médico", "medico@voll.med", "123456", Specialty.CARDIOLOGIA);

        // when ou act
        var freeDoctor = doctorRepository.chooseRandomDoctor(Specialty.CARDIOLOGIA, nextMondayAt10);

        // then ou assert
        assertThat(freeDoctor).isEqualTo(doctor);
    }

    private void registerConsultation(Doctor doctor, Patient patient, LocalDateTime data) {
        em.persist(new Consultation(null, doctor, patient, data, null));
    }

    private Doctor registerDoctor(String name, String email, String crm, Specialty specialty) {
        var doctor = new Doctor(doctorData(name, email, crm, specialty));
        em.persist(doctor);
        return doctor;
    }

    private Patient registerPatient(String name, String email, String cpf) {
        var patient = new Patient(patientData(name, email, cpf));
        em.persist(patient);
        return patient;
    }

    private DoctorDTO doctorData(String name, String email, String crm, Specialty specialty) {
        return new DoctorDTO(
                name,
                email,
                "61999999999",
                crm,
                specialty,
                addressData()
        );
    }

    private PatientDTO patientData(String name, String email, String cpf) {
        return new PatientDTO(
                name,
                email,
                "61999999999",
                cpf,
                addressData()
        );
    }

    private AddressDTO addressData() {
        return new AddressDTO(
                "rua xpto",
                "bairro",
                "00000000",
                "Brasilia",
                "DF",
                null,
                null
        );
    }
}