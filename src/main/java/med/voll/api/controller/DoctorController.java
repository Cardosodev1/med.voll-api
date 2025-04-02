package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.dto.DoctorListDTO;
import med.voll.api.dto.DoctorUpdateDTO;
import med.voll.api.entity.doctor.Doctor;
import med.voll.api.dto.DoctorDTO;
import med.voll.api.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorRepository repository;

    @PostMapping
    @Transactional
    public void register(@RequestBody @Valid DoctorDTO doctorDTO) {
        repository.save(new Doctor(doctorDTO));
    }

    @GetMapping
    public Page<DoctorListDTO> list(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable) {
        return repository.findAll(pageable)
                .map(DoctorListDTO::new);
    }

    @PutMapping
    @Transactional
    public void update(@RequestBody @Valid DoctorUpdateDTO doctorUpdateDTO) {
        var doctor = repository.getReferenceById(doctorUpdateDTO.id());
        doctor.update(doctorUpdateDTO);
    }

}