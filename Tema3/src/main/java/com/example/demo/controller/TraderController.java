package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/trader")
public class TraderController {

    @GetMapping("/dashboard")
    public ModelAndView traderDashboard() {
        return new ModelAndView("traderDashboard");
    }

    @GetMapping("/add")
    public ModelAndView addProduct() {
        return new ModelAndView("traderAddProduct");
    }
}
