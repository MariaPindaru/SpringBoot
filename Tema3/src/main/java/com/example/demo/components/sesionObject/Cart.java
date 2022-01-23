package com.example.demo.components.sesionObject;

import com.example.demo.dto.CartProductDto;
import com.example.demo.service.ProductTraderService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
@Scope("session")
public class Cart {
    private List<CartProductDto> cartProducts;
    private final ProductTraderService productTraderService;

    public Cart(ProductTraderService productTraderService) {
        this.productTraderService = productTraderService;
        cartProducts = new ArrayList<>();
    }

    public void addToCart(CartProductDto productDto){

        if(productDto.getQuantity() == null || productDto.getQuantity() <= 0) return;

        if(productTraderService.getProductById(productDto.getId()).get().getStock().getQuantity() < productDto.getQuantity()) return;

        Optional<CartProductDto> cartProduct = cartProducts.stream().filter(o -> Objects.equals(o.getId(), productDto.getId())).findFirst();
        if(cartProduct.isPresent()){
            Long prevQuantity = cartProduct.get().getQuantity();
            cartProducts.remove(cartProduct.get());
            productDto.setQuantity(productDto.getQuantity() + prevQuantity);
        }

        cartProducts.add(productDto);
    }

    public void updateProduct(CartProductDto productDto){

        if(productDto.getQuantity() == null || productDto.getQuantity() <= 0) return;

        if(productTraderService.getProductById(productDto.getId()).get().getStock().getQuantity() < productDto.getQuantity()) return;

        Optional<CartProductDto> cartProduct = cartProducts.stream().filter(o -> Objects.equals(o.getId(), productDto.getId())).findFirst();

        if(cartProduct.isPresent()){
           cartProduct.get().setQuantity(productDto.getQuantity());
        }
    }

    public List<CartProductDto> getCartProducts() {
        return cartProducts;
    }

    public void setCartProducts(List<CartProductDto> cartProducts) {
        this.cartProducts = cartProducts;
    }
}
