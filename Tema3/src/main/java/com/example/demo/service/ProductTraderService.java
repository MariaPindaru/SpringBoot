package com.example.demo.service;

import com.example.demo.model.ProductTrader;

import java.util.List;
import java.util.Optional;

public interface ProductTraderService {

    public List<ProductTrader> getAllTraderProducts();

    public List<ProductTrader> getProductsByTraderId(Long id);

    public Optional<ProductTrader> getProductById(Long id);

    public ProductTrader getProductByTraderNameAndProductName(String trader, String product);

    public void handleStockChange(ProductTrader productTrader);

    public void save(ProductTrader productTrader);
}