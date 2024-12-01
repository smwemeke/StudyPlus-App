package edu.miu.cs489.studyplus.secured;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/management")
public class ParticipantController2 {

    @GetMapping("/participant")
    public String participant(){
        return "Participant: secured endpoint";
    }
}
