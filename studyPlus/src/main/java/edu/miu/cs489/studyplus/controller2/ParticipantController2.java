package edu.miu.cs489.studyplus.controller2;

import edu.miu.cs489.studyplus.dto.request.AddressRequestDTO;
import edu.miu.cs489.studyplus.dto.request.ParticipantRequestDTO;
import edu.miu.cs489.studyplus.dto.response.ParticipantResponseDTO;
import edu.miu.cs489.studyplus.dto.response.StudyResponseDTO;
import edu.miu.cs489.studyplus.model.Participant;
import edu.miu.cs489.studyplus.service.ParticipantService;
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
    @RequestMapping("/participants")
    @RequiredArgsConstructor
    public class ParticipantController2 {

        private final ParticipantService participantService;
        private final StudyService studyService;

        @GetMapping
        public String getAllParticipants(Model model) {
            List<ParticipantResponseDTO> participants = participantService.getAllParticipants();
            model.addAttribute("participants", participants);
            return "participant-list";
        }

        @GetMapping(value = "/create")
        public ModelAndView createParticipantForm() {
            AddressRequestDTO addressRequestDTO = new AddressRequestDTO("", "", "", null);
            ParticipantRequestDTO participantRequestDTO = new ParticipantRequestDTO(
                    "", "", "", "", "", null, addressRequestDTO, null
            );
            ModelAndView mav = new ModelAndView("participant-create");
            mav.addObject("participant", participantRequestDTO);
            return mav;
        }

        @PostMapping
        public String createParticipant(@ModelAttribute("participant") @Valid ParticipantRequestDTO participantRequestDTO) {
            participantService.createParticipants(participantRequestDTO);
            return "redirect:/participants";
        }

        @GetMapping("/update/{username}")
        public String updateParticipantForm(@PathVariable String username, Model model) {
            Optional<ParticipantResponseDTO> participant = participantService.findParticipantByUsername(username);
            participant.ifPresent(value -> model.addAttribute("participant", value));
            return "participant-update"; // Corresponding Thymeleaf template
        }

        @PostMapping("/update/{username}")
        public String updateParticipant(@PathVariable String username,
                                        @ModelAttribute("participant") @Valid ParticipantRequestDTO participantRequestDTO) {
            participantService.updateParticipant(username, participantRequestDTO);
            return "redirect:/participants";
        }

        @PostMapping("/delete/{username}")
        public String deleteParticipant(@PathVariable String username, RedirectAttributes redirectAttributes) {
            participantService.deleteParticipantByUsername(username);
            redirectAttributes.addFlashAttribute("message", "Participant deleted successfully!");
            return "redirect:/participants";
        }

        @GetMapping("/assign-studies/{participantId}")
        public String assignStudiesForm(@PathVariable Long participantId, Model model) {
            List<StudyResponseDTO> studies = studyService.getAllStudies(); // Get all available studies
            model.addAttribute("participantId", participantId);
            model.addAttribute("studies", studies);
            return "assign-studies";
        }
        @PostMapping("/assign-studies/{participantId}")
        public String assignStudies(@PathVariable Long participantId,
                                    @RequestParam List<Long> studyIds,
                                    RedirectAttributes redirectAttributes) {
            participantService.assignStudies(participantId, studyIds);
            redirectAttributes.addFlashAttribute("message", "Studies assigned successfully!");
            return "redirect:/participants";
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
