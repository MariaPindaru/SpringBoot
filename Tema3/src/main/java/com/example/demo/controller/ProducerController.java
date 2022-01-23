package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/producer")
public class ProducerController {

    @GetMapping("/dashboard")
    public ModelAndView producerDashboard() {
        return new ModelAndView("producer/producerDashboard");
    }
}
