package com.example.demo.controller;

import com.example.demo.components.sesionObject.Cart;
import com.example.demo.dto.CartProductDto;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Scope("request")
public class CartController {
    @Autowired
    private Cart cart;

    @PostMapping("/addToCart")
    public String addToCart(Model model, @ModelAttribute("product") CartProductDto product) {

        cart.addToCart(product);

        return "redirect:/client/products";
    }

    @GetMapping("/viewCart")
    public String viewCart(Model model, RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("cardProducts", cart.getCartProducts());

        return "client/clientViewCart";
    }
}
