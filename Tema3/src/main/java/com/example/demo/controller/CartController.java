package com.example.demo.controller;

import com.example.demo.components.sesionObject.Cart;
import com.example.demo.dto.CartProductDto;
import com.example.demo.model.Order;
import com.example.demo.model.ProductOrder;
import com.example.demo.model.ProductTrader;
import com.example.demo.model.User;
import com.example.demo.service.OrderService;
import com.example.demo.service.ProductOrderService;
import com.example.demo.service.ProductTraderService;
import com.example.demo.service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.sql.Date;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@Scope("request")
public class CartController {
    @Autowired
    private Cart cart;

    private final OrderService orderService;
    private final UserService userService;
    private final ProductOrderService productOrderService;
    private final ProductTraderService productTraderService;
    private ModelMapper modelMapper;

    public CartController(OrderService orderService, UserService userService, ProductOrderService productOrderService, ProductTraderService productTraderService, ModelMapper modelMapper) {
        this.orderService = orderService;
        this.userService = userService;
        this.productOrderService = productOrderService;
        this.productTraderService = productTraderService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/addToCart")
    public String addToCart(Model model, @ModelAttribute("product") CartProductDto product) {

        cart.addToCart(product);

        return "redirect:/client/products";
    }

    @PostMapping("/updateProduct")
    public String updateProduct(Model model, @ModelAttribute("product") CartProductDto product) {

        cart.updateProduct(product);

        return "redirect:/viewCart";
    }


    @GetMapping("/viewCart")
    public String viewCart(Model model, RedirectAttributes redirectAttributes) {

        Double total = cart.getCartProducts().stream().map(o -> o.getTotalCost()).mapToDouble(Double::doubleValue).sum();

        redirectAttributes.addFlashAttribute("cartProducts", cart.getCartProducts());
        redirectAttributes.addFlashAttribute("totalCost", total);

        return "redirect:/client/cart";
    }

    @PostMapping(path = "/placeOrder")
    public String placeOrder(Model model, Principal principal) {

        if(cart.getCartProducts().isEmpty()) return "redirect:/viewCart";

        User user = userService.findByUsername(principal.getName());

        Order order = new Order();
        order.setClient(user);
        order.setDate(Date.from(Instant.now()));
        orderService.save(order);

        List<ProductOrder> productOrderList = cart.getCartProducts().stream()
                .map(this::convertToProductOrder)
                .collect(Collectors.toList());

        for (ProductOrder productOrder : productOrderList) {
            productOrder.setOrder(order);
            productOrderService.save(productOrder);

            ProductTrader productTrader =  productOrder.getProductTrader();
            if(productTrader.isSubscription()) {
                productTraderService.handleStockChange(productTrader);
            }
        }

        cart.clearCart();

        return "redirect:/client/products";
    }

    private ProductOrder convertToProductOrder(CartProductDto cartProductDto) {

        ProductOrder productOrder = new ProductOrder();
        productOrder.setProductTrader(productTraderService.getProductByTraderNameAndProductName(cartProductDto.getTrader(), cartProductDto.getProductName()));
        productOrder.setQuantity(cartProductDto.getQuantity());

        return productOrder;
    }

}
