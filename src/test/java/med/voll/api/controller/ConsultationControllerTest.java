package med.voll.api.controller;

import med.voll.api.domain.dto.consultation.ConsultationDTO;
import med.voll.api.domain.dto.consultation.ConsultationDetailsDTO;
import med.voll.api.domain.entity.doctor.Specialty;
import med.voll.api.domain.service.ConsultationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ConsultationControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<ConsultationDTO> consultationDTOJson;

    @Autowired
    private JacksonTester<ConsultationDetailsDTO> consultationDetailsDTOJson;

    @MockitoBean
    private ConsultationService consultationService;

    @Test
    @DisplayName("Deveria devolver código http 400 quando informações estão inválidas")
    @WithMockUser
    void scheduleScenario1() throws Exception {
        var response = mvc.perform(post("/consultations"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria devolver código http 200 quando informações estão validas")
    @WithMockUser
    void scheduleScenario2() throws Exception {
        var date = LocalDateTime.now().plusHours(1);
        var specialty = Specialty.CARDIOLOGIA;

        var consultationDetails = new ConsultationDetailsDTO(null, 2l, 5l, date);
        when(consultationService.schedule(any())).thenReturn(consultationDetails);

        var response = mvc.perform(
                        post("/consultations")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(consultationDTOJson.write(
                                        new ConsultationDTO(2l, 5l, date, specialty)
                                ).getJson())
                )
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var jsonExpected = consultationDetailsDTOJson.write(
                consultationDetails
        ).getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonExpected);
    }

}