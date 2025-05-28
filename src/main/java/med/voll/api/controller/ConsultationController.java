package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.domain.dto.consultation.ConsultationCancellationDTO;
import med.voll.api.domain.dto.consultation.ConsultationDTO;
import med.voll.api.domain.dto.consultation.ConsultationDetailsDTO;
import med.voll.api.domain.service.ConsultationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultations")
@SecurityRequirement(name = "bearer-key")
public class ConsultationController {

    @Autowired
    private ConsultationService service;

    @PostMapping
    @Transactional
    public ResponseEntity schedule(@RequestBody @Valid ConsultationDTO dto) {
        var details = service.schedule(dto);
        return ResponseEntity.ok(details);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancel(@RequestBody @Valid ConsultationCancellationDTO dto) {
        service.cancel(dto);
        return ResponseEntity.noContent().build();
    }

}
