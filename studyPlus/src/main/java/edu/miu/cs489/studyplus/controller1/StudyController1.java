package edu.miu.cs489.studyplus.controller1;

import edu.miu.cs489.studyplus.dto.request.StudyRequestDTO;
import edu.miu.cs489.studyplus.dto.response.ParticipantResponseDTO;
import edu.miu.cs489.studyplus.dto.response.StudyResponseDTO;
import edu.miu.cs489.studyplus.service.StudyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/studies")
@RequiredArgsConstructor
public class StudyController1 {

        private final StudyService studyService;
        //String result = "redirect:/studies";

        @GetMapping
        public String getAllStudies(Model model) {
            List<StudyResponseDTO> studies = studyService.getAllStudies();
            model.addAttribute("studies", studies);
            return "study-list";
        }

        @GetMapping(value = "/create")
        public ModelAndView createStudyForm() {
            StudyRequestDTO studyRequestDTO = new StudyRequestDTO(
                    "", "", null, null, ""
            );
            ModelAndView mav = new ModelAndView("study-create");
            mav.addObject("study", studyRequestDTO);
            return mav;
        }

        @PostMapping
        public String createStudy(@ModelAttribute("study") @Valid StudyRequestDTO studyRequestDTO) {
            studyService.createStudy(studyRequestDTO);
            return "redirect:/studies";
        }

        @GetMapping("/update/{studyName}")
        public String updateStudyForm(@PathVariable String studyName, Model model) {
            Optional<StudyResponseDTO> study = studyService.findStudyByName(studyName);
            study.ifPresent(value -> model.addAttribute("study", value));
            return "study-update";
        }
        @PostMapping("/update/{studyName}")
        public String updateStudy(@PathVariable String studyName, @ModelAttribute("study") @Valid StudyRequestDTO studyRequestDTO) {
            studyService.updateStudy(studyName, studyRequestDTO);
            return "redirect:/studies";
        }
        @PostMapping("/delete/{studyName}")
        public String deleteStudy(@PathVariable String studyName, RedirectAttributes redirectAttributes) {
            studyService.deleteStudyByStudyName(studyName);
            redirectAttributes.addFlashAttribute("message", "Study deleted successfully!");
            return "redirect:/studies";
        }
    @GetMapping("/view-participants/{studyId}")
    public String viewParticipants(@PathVariable Long studyId, Model model) {
        // Fetch participants associated with the study
        List<ParticipantResponseDTO> participants = studyService.getParticipantsByStudyId(studyId);

        // Add participants and studyId to the model
        model.addAttribute("participants", participants);
        model.addAttribute("studyId", studyId);

        return "view-participants"; // Refers to view-participants.html
    }



}
