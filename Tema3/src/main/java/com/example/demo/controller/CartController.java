package com.example.demo.controller;

import com.example.demo.components.sesionObject.Cart;
import com.example.demo.dto.CartProductDto;
import com.example.demo.dto.ProductTraderCreationDto;
import com.example.demo.dto.ProductTraderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Scope("request")
public class CartController {
    @Autowired
    private Cart cart;

    @PostMapping("/addToCart")
    public String addToCart(Model model, @ModelAttribute("product")
            CartProductDto creationObject)
    {
        int x = 0;
        return "client/clientViewProducts";
    }
}
