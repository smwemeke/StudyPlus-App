package edu.miu.cs489.studyplus.controller2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
        @GetMapping("/studyplus")
        public String home() {
            return "homePage"; // Name of the Thymeleaf template for the homepage
        }
    }
