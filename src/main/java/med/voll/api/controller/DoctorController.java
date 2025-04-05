package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.dto.DoctorListDTO;
import med.voll.api.domain.dto.DoctorUpdateDTO;
import med.voll.api.domain.dto.DoctorDetailsDTO;
import med.voll.api.domain.entity.doctor.Doctor;
import med.voll.api.domain.dto.DoctorDTO;
import med.voll.api.domain.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid DoctorDTO doctorDTO, UriComponentsBuilder uriBuilder) {
        var doctor = new Doctor(doctorDTO);
        repository.save(doctor);

        var uri = uriBuilder.path("/doctors/{id}").buildAndExpand(doctor.getId()).toUri();

        return ResponseEntity.created(uri).body(new DoctorDetailsDTO(doctor));
    }

    @GetMapping
    public ResponseEntity<Page<DoctorListDTO>> list(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable) {
        var page = repository.findAllByActiveTrue(pageable)
                .map(DoctorListDTO::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid DoctorUpdateDTO doctorUpdateDTO) {
        var doctor = repository.getReferenceById(doctorUpdateDTO.id());
        doctor.update(doctorUpdateDTO);

        return ResponseEntity.ok(new DoctorDetailsDTO(doctor));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
        var doctor = repository.getReferenceById(id);
        doctor.delete();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detail(@PathVariable Long id) {
        var doctor = repository.getReferenceById(id);
        return ResponseEntity.ok(new DoctorDetailsDTO(doctor));
    }

}