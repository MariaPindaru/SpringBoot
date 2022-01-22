package com.example.demo.controller;

import com.example.demo.dto.ProductProducerDto;
import com.example.demo.dto.ProductTraderDto;
import com.example.demo.model.ProductProducer;
import com.example.demo.model.ProductTrader;
import com.example.demo.model.User;
import com.example.demo.service.ProductProducerService;
import com.example.demo.service.ProductTraderService;
import com.example.demo.service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/trader")
public class TraderController {

    private final ProductTraderService productTraderService;
    private final ProductProducerService productProducerService;
    private final UserService userService;

    @Autowired
    private ModelMapper modelMapper;

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
        for (ProductProducer pp : allProducts) {
            if (existingProducts
                    .stream()
                    .noneMatch(o -> Objects.equals(o.getProductProducer().getProduct().getId(), pp.getId()))) {
                remainingProducts.add(pp);
            }
        }

        List<ProductProducerDto> list = remainingProducts.stream()
                .map(this::convertToProductProducerDto)
                .collect(Collectors.toList());

        model.addAttribute("products", list);

        return "traderAddProduct";
    }

    @GetMapping("/all")
    public String viewProducts(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());

        List<ProductTrader> products = productTraderService.getProductsByTraderId(user.getId());

        List<ProductTraderDto> list = products.stream()
                .map(this::convertToProductTraderDto)
                .collect(Collectors.toList());

        model.addAttribute("products", list);

        return "traderViewProducts";
    }

    private ProductTraderDto convertToProductTraderDto(ProductTrader productTrader) {

        TypeMap<ProductTrader, ProductTraderDto> propertyMapper = this.modelMapper.getTypeMap(ProductTrader.class, ProductTraderDto.class);

        if (propertyMapper == null) {
            propertyMapper = this.modelMapper.createTypeMap(ProductTrader.class, ProductTraderDto.class);
            propertyMapper.addMappings(mapper -> {
//            mapper.map(src -> src.getId(), ProductTraderDto::setId);
                mapper.map(src -> src.getTrader().getName(), ProductTraderDto::setTrader);
                mapper.map(src -> src.getProductProducer().getProducer().getName(), ProductTraderDto::setProducer);
                mapper.map(src -> src.getProductProducer().getProduct().getName(), ProductTraderDto::setProduct);
                mapper.map(src -> src.getProductProducer().getPrice(), ProductTraderDto::setPrice);
                mapper.map(src -> src.getStock().getQuantity(), ProductTraderDto::setQuantity);
                mapper.map(src -> src.getStock().getMaxQuantity(), ProductTraderDto::setMaxQuantity);
                mapper.map(src -> src.getStock().getMinQuantity(), ProductTraderDto::setMinQuantity);
            });
        }

        return modelMapper.map(productTrader, ProductTraderDto.class);
    }

    private ProductProducerDto convertToProductProducerDto(ProductProducer productProducer) {

        TypeMap<ProductProducer, ProductProducerDto> propertyMapper = this.modelMapper.getTypeMap(ProductProducer.class, ProductProducerDto.class);

        if (propertyMapper == null) {
            propertyMapper = this.modelMapper.createTypeMap(ProductProducer.class, ProductProducerDto.class);
            propertyMapper.addMappings(mapper -> {
//            mapper.map(src -> src.getId(), ProductProducerDto::setId);
                mapper.map(src -> src.getProducer().getName(), ProductProducerDto::setProducer);
                mapper.map(src -> src.getProduct().getName(), ProductProducerDto::setProductName);
//            mapper.map(src -> src.getPrice(), ProductProducerDto::setPrice);
            });
        }

        return  modelMapper.map(productProducer, ProductProducerDto.class);
    }
}
