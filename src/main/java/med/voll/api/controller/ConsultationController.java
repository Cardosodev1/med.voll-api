package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.dto.consultation.ConsultationDTO;
import med.voll.api.domain.dto.consultation.ConsultationDetailsDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("consultations")
public class ConsultationController {

    @PostMapping
    @Transactional
    public ResponseEntity schedule(@RequestBody @Valid ConsultationDTO dto) {
        System.out.println(dto);
        return ResponseEntity.ok(new ConsultationDetailsDTO(null, null, null, null));
    }

}
