package com.example.demo.controller;

import com.example.demo.service.SecurityService;
import com.example.demo.service.UserService;
import com.example.demo.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ClientController {
    @GetMapping("/clientDashboard")
    public ModelAndView clientDashboard() {
        return new ModelAndView("clientDashboard");
    }

}
