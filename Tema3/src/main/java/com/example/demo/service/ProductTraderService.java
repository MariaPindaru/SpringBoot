package com.example.demo.service;

import com.example.demo.model.ProductTrader;
import com.example.demo.model.User;

import java.util.List;
import java.util.Optional;

public interface ProductTraderService {

    public List<ProductTrader> getAllTraderProducts();

    public List<ProductTrader> getProductsByTraderId(Long id);

    public Optional<ProductTrader> getProductById(Long id);

    public void save(ProductTrader productTrader);
}