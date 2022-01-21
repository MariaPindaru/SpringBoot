package com.example.demo.controller;

import com.example.demo.service.UserService;
import com.example.demo.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ApplicationController {
    private final UserService userService;
    private final SecurityService securityService;
    private final UserValidator userValidator;

    @Autowired
    public ApplicationController(UserService userService, SecurityService securityService, UserValidator userValidator) {
        this.userService = userService;
        this.securityService = securityService;
        this.userValidator = userValidator;
    }

    @GetMapping({"/","/login"})
    public String login(Model model, String error, String logout) {
//        if (securityService.isAuthenticated()) {
//            return "redirect:/";
//        }

        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @GetMapping("/producerDashboard")
    public ModelAndView producerDashboard() {
        return new ModelAndView("producerDashboard");
    }

    @GetMapping("/traderDashboard")
    public ModelAndView traderDashboard() {
        return new ModelAndView("traderDashboard");
    }

    @GetMapping("/clientDashboard")
    public ModelAndView clientDashboard() {
        return new ModelAndView("clientDashboard");
    }

}
