package edu.miu.cs489.studyplus.controller2;

import edu.miu.cs489.studyplus.dto.request.StudyRequestDTO;
import edu.miu.cs489.studyplus.dto.response.StudyResponseDTO;
import edu.miu.cs489.studyplus.service.StudyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/studies")
@RequiredArgsConstructor
public class StudyController2 {

        private final StudyService studyService;

        @GetMapping
        public String getAllStudies(Model model) {
            List<StudyResponseDTO> studies = studyService.getAllStudies();
            model.addAttribute("studies", studies);
            return "study-list";
        }
    @Controller
    @RequestMapping("/studies")
    public class StudyWebController {

        private final StudyService studyService;

        public StudyWebController(StudyService studyService) {
            this.studyService = studyService;
        }

        @GetMapping("/create")
        public String createStudyForm(Model model) {
            model.addAttribute("study", new StudyRequestDTO(
                    "", // Placeholder values for the record fields
                    "",
                    null,
                    null,
                    ""
            ));
            return "study-create"; // This should match the template name in the templates folder
        }
    }


        @PostMapping
        public String createStudy(@ModelAttribute("study") @Valid StudyRequestDTO studyRequestDTO) {
            studyService.createStudy(studyRequestDTO);
            return "redirect:/studies";
        }

        @GetMapping("/{studyName}")
        public String viewStudy(@PathVariable String studyName, Model model) {
            Optional<StudyResponseDTO> study = studyService.findStudyByName(studyName);
            if (study.isPresent()) {
                model.addAttribute("study", study.get());
            }
            return "study-view";
        }

        @GetMapping("/edit/{studyName}")
        public String editStudyForm(@PathVariable String studyName, Model model) {
            Optional<StudyResponseDTO> study = studyService.findStudyByName(studyName);
            study.ifPresent(value -> model.addAttribute("study", value));
            return "study-edit";
        }

        @PostMapping("/edit/{studyName}")
        public String editStudy(@PathVariable String studyName, @ModelAttribute("study") @Valid StudyRequestDTO studyRequestDTO) {
            studyService.updateStudy(studyName, studyRequestDTO);
            return "redirect:/studies";
        }

        @PostMapping("/delete/{studyName}")
        public String deleteStudy(@PathVariable String studyName) {
            studyService.deleteStudyByStudyName(studyName);
            return "redirect:/studies";
        }
    }
