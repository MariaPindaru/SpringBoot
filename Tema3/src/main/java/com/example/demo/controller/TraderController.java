package com.example.demo.controller;

import com.example.demo.model.ProductProducer;
import com.example.demo.model.ProductTrader;
import com.example.demo.model.User;
import com.example.demo.service.ProductProducerService;
import com.example.demo.service.ProductTraderService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/trader")
public class TraderController {

    private final ProductTraderService productTraderService;
    private final ProductProducerService productProducerService;
    private final UserService userService;

    @Autowired
    public TraderController(ProductTraderService productTraderService, ProductProducerService productProducerService, UserService userService) {
        this.productTraderService = productTraderService;
        this.productProducerService = productProducerService;
        this.userService = userService;
    }

    @GetMapping("/dashboard")
    public ModelAndView traderDashboard() {
        return new ModelAndView("traderDashboard");
    }

    @GetMapping("/add")
    public String addProduct(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());

        List<ProductProducer> allProducts = productProducerService.getAllProducerProducts();
        List<ProductTrader> existingProducts = productTraderService.getProductsByTraderId(user.getId());

        List<ProductProducer> remainingProducts = new ArrayList<>();
        for(ProductProducer pp : allProducts){
            if( existingProducts
                        .stream()
                        .filter(o -> o.getProduct().getProduct().getId() == pp.getId())
                        .findAny().isPresent() == false ){
                remainingProducts.add(pp);
            }
        }

//        List<ProductProducer> filteredList = allProducts.removeIf(product ->
//
//                existingProducts
//                        .stream()
//                        .filter(o -> o.getProduct().getProduct().getId() == product.getId())
//                        .findAny().isPresent() == true );

        model.addAttribute("products", remainingProducts);

        return "traderAddProduct";
    }

    @GetMapping("/all")
    public String viewProducts(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());

        List<ProductTrader> products = productTraderService.getProductsByTraderId(user.getId());

        model.addAttribute("products", products);

        return "traderViewProducts";
    }
}
