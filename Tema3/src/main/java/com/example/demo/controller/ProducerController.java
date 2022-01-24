package com.example.demo.controller;

import com.example.demo.Utils.Utils;
import com.example.demo.dto.ProductProducerDto;
import com.example.demo.dto.ProductTraderDto;
import com.example.demo.model.ProductProducer;
import com.example.demo.model.User;
import com.example.demo.service.ProductProducerService;
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
@RequestMapping("/producer")
public class ProducerController {
    private final ProductProducerService productProducerService;
    private final UserService userService;
    private ModelMapper modelMapper;

    public ProducerController(ProductProducerService productProducerService, UserService userService, ModelMapper modelMapper) {
        this.productProducerService = productProducerService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/dashboard")
    public ModelAndView producerDashboard() {
        return new ModelAndView("producer/producerDashboard");
    }

    @GetMapping("/products")
    public String viewProducts(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());

        List<ProductProducer> products = productProducerService.getProductProducerByProducer(user);

        List<ProductProducerDto> list = products.stream()
                .map(o -> Utils.convertToProductProducerDto(o, modelMapper))
                .collect(Collectors.toList());

        model.addAttribute("products", list);

        return "producer/producerViewProducts";
    }
}
