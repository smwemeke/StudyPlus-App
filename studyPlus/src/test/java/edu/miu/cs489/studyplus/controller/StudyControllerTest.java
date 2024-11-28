package edu.miu.cs489.studyplus.controller;

import com.google.gson.Gson;
import edu.miu.cs489.studyplus.dto.request.StudyRequestDTO;
import edu.miu.cs489.studyplus.dto.response.StudyResponseDTO;
import edu.miu.cs489.studyplus.model.Study;
import edu.miu.cs489.studyplus.service.StudyService;
import edu.miu.cs489.studyplus.util.GsonConfig;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@WebMvcTest(controllers = StudyController.class)
class StudyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudyService studyService;

   Gson gson = GsonConfig.createGsonWithLocalDateSupport();
    @Autowired
    private StudyController studyController;

    @Test
    void createStudies() throws Exception{
        StudyRequestDTO studyRequestDTO =
                new StudyRequestDTO(
                        "COVID",
                        "Vaccine for COVID",
                        LocalDate.of(2021,04,10),
                        LocalDate.of(2030,04,20),
                        "NIH");
        StudyResponseDTO studyResponseDTO =
                new StudyResponseDTO(2L,"COVID",
                        "Vaccine for COVID",
                        LocalDate.of(2021,04,10),
                        LocalDate.of(2030,04,20),
                        "NIH"
                );
        Mockito.when(studyService.createStudy(studyRequestDTO)).thenReturn(Optional.of(studyResponseDTO));
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v1/studies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(studyRequestDTO))
        )
                .andDo(MockMvcResultHandlers.print())
                .andExpectAll(
                        MockMvcResultMatchers.content().json(gson.toJson(studyResponseDTO))
                );
    }

    @Test
    void getAllStudies() throws Exception{
        // Create Mock behavior
        List<StudyResponseDTO> responseDTOList = new ArrayList<>();
        responseDTOList.add(new StudyResponseDTO(1L,"Malaria","Treat Malaria",LocalDate.of(2020,10,30),LocalDate.of(2030,10,30),"WHO"));
        responseDTOList.add(new StudyResponseDTO(2L,"COVID","Treat COVID",LocalDate.of(2021,05,30),LocalDate.of(2030,05,30),"NIH"));

        //Define what the mock sholud do when getAllStudies method is called
        Mockito.when(studyService.getAllStudies()).thenReturn(responseDTOList);
        mockMvc.perform(
               MockMvcRequestBuilders.get("/api/v1/studies")
                       .contentType(MediaType.APPLICATION_JSON)
                       .content(gson.toJson(responseDTOList))

        )
                .andDo(MockMvcResultHandlers.print())
                .andExpectAll(MockMvcResultMatchers.content().json(gson.toJson(responseDTOList)));
    }
    @Test
    void deleteStudy() throws Exception{
       mockMvc.perform(
               MockMvcRequestBuilders.delete("/api/v1/studies/sue")
       )
               .andDo(MockMvcResultHandlers.print())
               .andExpectAll(MockMvcResultMatchers.status().isNoContent());
    }
    @Test
    void findStudyByStudyName() throws Exception {
        //Mock behavior
        String studyName = "COVID";
        StudyResponseDTO studyResponseDTO = new StudyResponseDTO(1L,"COVID","Treat COVID",LocalDate.of(2020,12,01),LocalDate.of(2030,12,10),"JHU");

        // define what mock should do
        Mockito.when(studyService.findStudyByName(studyName)).thenReturn(Optional.of(studyResponseDTO));

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/studies/{studyName}", studyName)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(studyResponseDTO))
        )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.content().json(gson.toJson(studyResponseDTO)));
    }
    @Test
    void updateStudy() throws Exception {
        // Input
        StudyRequestDTO studyRequestDTO = new StudyRequestDTO(
                "COVID",
                "Treat COVID",
                LocalDate.of(2021,12,23),
                LocalDate.of(2026,11,19),
                "WHO");
        // Expected Output
        StudyResponseDTO studyResponseDTO = new StudyResponseDTO(
                1L,"COVID",
                "Treat COVID",
                LocalDate.of(2021,12,23),
                LocalDate.of(2026,11,19),
                "WHO");

        Mockito.when(studyService.updateStudy("TB",studyRequestDTO)).thenReturn(Optional.of(studyResponseDTO));

        mockMvc.perform(
                MockMvcRequestBuilders.put("/api/v1/studies/TB")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(studyRequestDTO))
        )
                .andDo(MockMvcResultHandlers.print())
                .andExpectAll(MockMvcResultMatchers.content().json(gson.toJson(studyResponseDTO)));
    }
}