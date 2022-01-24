package com.example.demo.controller;

import com.example.demo.Utils.Utils;
import com.example.demo.components.validator.ProductTraderCreationValidator;
import com.example.demo.components.validator.ProductTraderValidator;
import com.example.demo.dto.ProductProducerDto;
import com.example.demo.dto.ProductTraderCreationDto;
import com.example.demo.dto.ProductTraderDto;
import com.example.demo.model.*;
import com.example.demo.service.*;
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
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/trader")
public class TraderController {

    private final ProductTraderService productTraderService;
    private final ProductProducerService productProducerService;
    private final ProductService productService;
    private final UserService userService;
    private final StockService stockService;
    private final ProductTraderCreationValidator productTraderCreationValidator;
    private final ProductTraderValidator productTraderValidator;
    private ModelMapper modelMapper;

    @Autowired
    public TraderController(ProductTraderService productTraderService, ProductProducerService productProducerService, ProductService productService, UserService userService, StockService stockService, ProductTraderCreationValidator productTraderValidator, ProductTraderValidator productTraderValidator1, ModelMapper modelMapper) {
        this.productTraderService = productTraderService;
        this.productProducerService = productProducerService;
        this.productService = productService;
        this.userService = userService;
        this.stockService = stockService;
        this.productTraderCreationValidator = productTraderValidator;
        this.productTraderValidator = productTraderValidator1;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/dashboard")
    public ModelAndView traderDashboard() {
        return new ModelAndView("trader/traderDashboard");
    }

    @GetMapping("/add")
    public String addProduct(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());

        List<Product> products = productService.getAllProducts();
        List<ProductProducer> allProducts = productProducerService.getAllProducerProducts();
        List<ProductTrader> existingProducts = productTraderService.getProductsByTraderId(user.getId());

        List<ProductProducer> cheapestProducts = new ArrayList<>();
        for (Product product : products) {
            List<ProductProducer> productProducers = productProducerService.getAllProductProducerByProduct(product);

            if (productProducers.isEmpty()) continue;

            Collections.sort(productProducers, (p1, p2) -> p1.getPrice().compareTo(p1.getPrice()));
            cheapestProducts.add(productProducers.get(0));
        }

        List<ProductProducer> remainingProducts = new ArrayList<>();
        for (ProductProducer productProducer : cheapestProducts) {
            if (existingProducts
                    .stream()
                    .noneMatch(o -> Objects.equals(o.getProductProducer().getProduct().getId(), productProducer.getId()))) {
                remainingProducts.add(productProducer);
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
                                RedirectAttributes redirectAttributes, BindingResult bindingResult, Principal principal) {

        productTraderCreationValidator.validate(creationObject, bindingResult);

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.productTraderCreationDto", bindingResult);
            redirectAttributes.addFlashAttribute("productTraderCreationDto", creationObject);
            return "redirect:/trader/add";
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

    @GetMapping("/updateProduct")
    public String updateProducts(Model model, @ModelAttribute("id") Long id, Principal principal) {

        if (!model.containsAttribute("productTrader")) {
            ProductTraderDto converted = Utils.convertToProductTraderUpdateStockDto(productTraderService.getProductById(id).get(), modelMapper);
            model.addAttribute("productTrader", converted);
        }
        return "trader/traderUpdateStock";
    }

    @PostMapping("/updateProduct")
    public String updateProducts(Model model, @ModelAttribute("productTrader") ProductTraderDto productTrader, RedirectAttributes redirectAttributes, BindingResult bindingResult, Principal principal) {

        productTraderValidator.validate(productTrader, bindingResult);

        if (bindingResult.hasErrors()) {
            return "trader/traderUpdateStock";
        }

        Optional<ProductTrader> productTraderToUpdate = productTraderService.getProductById(productTrader.getId());
        if (productTraderToUpdate.isPresent()) {

            Stock stock = productTraderToUpdate.get().getStock();
            stock.setMaxQuantity(productTrader.getMaxQuantity());
            stock.setMinQuantity(productTrader.getMinQuantity());
            stock.setQuantity(productTrader.getQuantity());
            stockService.save(stock);

            if (productTrader.isSubscription() != productTraderToUpdate.get().isSubscription()) {

                productTraderToUpdate.get().setSubscription(productTrader.isSubscription());
                productTraderService.save(productTraderToUpdate.get());

                if (productTrader.isSubscription()) {
                    productTraderService.handleStockChange(productTraderToUpdate.get());
                }
            }
        }

        return "redirect:/trader/all";
    }
}
