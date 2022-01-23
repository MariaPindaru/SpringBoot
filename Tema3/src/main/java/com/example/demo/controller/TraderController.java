package com.example.demo.controller;

import com.example.demo.Utils.Utils;
import com.example.demo.dto.ProductProducerDto;
import com.example.demo.dto.ProductTraderCreationDto;
import com.example.demo.dto.ProductTraderDto;
import com.example.demo.model.ProductProducer;
import com.example.demo.model.ProductTrader;
import com.example.demo.model.Stock;
import com.example.demo.model.User;
import com.example.demo.service.ProductProducerService;
import com.example.demo.service.ProductTraderService;
import com.example.demo.service.StockService;
import com.example.demo.service.UserService;
import com.example.demo.validator.ProductTraderValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    private final StockService stockService;
    private final ProductTraderValidator productTraderValidator;
    private ModelMapper modelMapper;

    @Autowired
    public TraderController(ProductTraderService productTraderService, ProductProducerService productProducerService, UserService userService, StockService stockService, ProductTraderValidator productTraderValidator, ModelMapper modelMapper) {
        this.productTraderService = productTraderService;
        this.productProducerService = productProducerService;
        this.userService = userService;
        this.stockService = stockService;
        this.productTraderValidator = productTraderValidator;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/dashboard")
    public ModelAndView traderDashboard() {
        return new ModelAndView("trader/traderDashboard");
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
                .map(o -> Utils.convertToProductProducerDto(o, modelMapper))
                .collect(Collectors.toList());

        model.addAttribute("products", list);
        if (!model.containsAttribute("productTraderCreationDto"))
            model.addAttribute("productTraderCreationDto", new ProductTraderCreationDto());

        return "trader/traderAddProduct";
    }

    @PostMapping("/add")
    public String addNewProduct(Model model, @ModelAttribute("productTraderCreationDto") ProductTraderCreationDto creationObject,
                                RedirectAttributes ra, BindingResult bindingResult, Principal principal) {

        productTraderValidator.validate(creationObject, bindingResult);

        if (bindingResult.hasErrors()) {
            return "trader/traderAddProduct";
        }

        User user = userService.findByUsername(principal.getName());
        ProductProducer productProducer = productProducerService.getProductProducerById(creationObject.getProductProducerId());

        Stock stock = new Stock();
        stock.setQuantity(creationObject.getBuyQuantity());
        stock.setMinQuantity(creationObject.getMinQuantity());
        stock.setMaxQuantity(creationObject.getMaxQuantity());
        stockService.save(stock);

        ProductTrader productTrader = new ProductTrader();
        productTrader.setTrader(user);
        productTrader.setProductProducer(productProducer);
        productTrader.setStock(stock);
        productTraderService.save(productTrader);

        return "redirect:/trader/add";
    }

    @GetMapping("/all")
    public String viewProducts(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());

        List<ProductTrader> products = productTraderService.getProductsByTraderId(user.getId());

        List<ProductTraderDto> list = products.stream()
                .map(o -> Utils.convertToProductTraderDto(o, modelMapper))
                .collect(Collectors.toList());

        model.addAttribute("products", list);

        return "trader/traderViewProducts";
    }
}
