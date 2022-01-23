package com.example.demo.controller;

import com.example.demo.Utils.Utils;
import com.example.demo.dto.OrderDto;
import com.example.demo.dto.ProductTraderDto;
import com.example.demo.model.Order;
import com.example.demo.model.ProductTrader;
import com.example.demo.model.User;
import com.example.demo.service.OrderService;
import com.example.demo.service.ProductTraderService;
import com.example.demo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/client")
public class ClientController {

    private final ProductTraderService productTraderService;
    private final UserService userService;
    private final OrderService orderService;
    private ModelMapper modelMapper;

    public ClientController(ProductTraderService productTraderService, UserService userService, OrderService orderService, ModelMapper modelMapper) {
        this.productTraderService = productTraderService;
        this.userService = userService;
        this.orderService = orderService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/dashboard")
    public ModelAndView clientDashboard() {
        return new ModelAndView("clientDashboard");
    }

    @GetMapping("/products")
    public String viewProducts(Model model, Principal principal) {

        List<ProductTrader> products = productTraderService.getAllTraderProducts();

        List<ProductTraderDto> list = products.stream()
                .map(o -> Utils.convertToProductTraderDto(o, modelMapper))
                .collect(Collectors.toList());

        model.addAttribute("products", list);

        return "clientViewProducts";
    }

    @GetMapping("/orders")
    public String viewOrders(Model model, Principal principal) {

        User user = userService.findByUsername(principal.getName());

        List<Order> orders = orderService.getAllOrdersByUser(user);

        List<OrderDto> list = orders.stream()
                .map(o -> Utils.convertToOrderDto(o, modelMapper))
                .collect(Collectors.toList());

        model.addAttribute("orders", list);

        return "clientViewOrders";
    }
}
