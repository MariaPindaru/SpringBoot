package com.example.demo.components.sesionObject;

import com.example.demo.dto.CartProductDto;
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

    public Cart() {
        cartProducts = new ArrayList<>();
    }

    public void addToCart(CartProductDto productDto){

        Optional<CartProductDto> cartProduct = cartProducts.stream().filter(o -> Objects.equals(o.getId(), productDto.getId())).findFirst();
        if(cartProduct.isPresent()){
            Long prevQuantity = cartProduct.get().getQuantity();
            cartProducts.remove(cartProduct.get());
            productDto.setQuantity(productDto.getQuantity() + prevQuantity);
        }

        cartProducts.add(productDto);
    }

    public List<CartProductDto> getCartProducts() {
        return cartProducts;
    }

    public void setCartProducts(List<CartProductDto> cartProducts) {
        this.cartProducts = cartProducts;
    }
}
