package com.example.tema3.controller;

import com.example.tema3.service.UserService;
import com.example.tema3.service.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

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

//    /***
//     * @param model supply attributes used for rendering views
//     * @param principal is the currently logged in user
//     * @return what view to use
//     */
//    @GetMapping("/receipts")
//    public String showTable(Model model, Principal principal) {
//        User user = userService.findByUsername(principal.getName());
//
//        System.out.println("THE CURRENT USER " + principal.getName());
//        List<Receipt> receipts = receiptService.getReceiptsByUser(user);
//
//        model.addAttribute("receipts", receipts);
//        return "receipts";
//    }
//
//

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (securityService.isAuthenticated()) {
            return "redirect:/";
        }

        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @GetMapping({"/", "/welcome"})
    public String welcome(Model model) {
        return "welcome";
    }
}
