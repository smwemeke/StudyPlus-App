package edu.miu.cs489.studyplus.secured;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/coordinator")
public class CoordinatorController {

    @GetMapping
    public String coordinator(){
        return "coordinator: secured endpoint";
    }
}
