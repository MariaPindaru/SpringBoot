package com.example.demo.service;

import com.example.demo.model.ProductTrader;
import com.example.demo.model.User;

import java.util.List;

public interface ProductTraderService {

    public List<ProductTrader> getProducts();

    public List<ProductTrader> getProductsByTraderId(Long id);

    public void save(ProductTrader productTrader);
}