package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProducerController {

    @GetMapping("/producerDashboard")
    public ModelAndView producerDashboard() {
        return new ModelAndView("producerDashboard");
    }
}
