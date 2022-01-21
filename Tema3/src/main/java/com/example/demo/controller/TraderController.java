package com.example.demo.controller;

import com.example.demo.model.ProductTrader;
import com.example.demo.model.User;
import com.example.demo.service.ProductTraderService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/trader")
public class TraderController {

    private final ProductTraderService productTraderService;
    private final UserService userService;

    @Autowired
    public TraderController(ProductTraderService productTraderService, UserService userService) {
        this.productTraderService = productTraderService;
        this.userService = userService;
    }

    @GetMapping("/dashboard")
    public ModelAndView traderDashboard() {
        return new ModelAndView("traderDashboard");
    }

    @GetMapping("/add")
    public ModelAndView addProduct() {
        return new ModelAndView("traderAddProduct");
    }

    @GetMapping("/all")
    public String viewProducts(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());

        System.out.println("THE CURRENT USER " + principal.getName());
        List<ProductTrader> products = productTraderService.getProductsByTraderId(user.getId());

        model.addAttribute("products", products);

        return "traderViewProducts";
    }
}
