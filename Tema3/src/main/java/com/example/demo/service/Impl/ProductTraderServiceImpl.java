package com.example.demo.service.Impl;

import com.example.demo.dao.repository.ProductTraderRepository;
import com.example.demo.model.ProductTrader;
import com.example.demo.model.User;
import com.example.demo.service.ProductTraderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductTraderServiceImpl implements ProductTraderService {

    private final ProductTraderRepository productTraderRepository;

    @Autowired
    public ProductTraderServiceImpl(ProductTraderRepository productTraderRepository) {
        this.productTraderRepository = productTraderRepository;
    }


    @Override
    public List<ProductTrader> getProducts() {
        return this.productTraderRepository.findAll();
    }

    @Override
    public List<ProductTrader> getProductsByTrader(User user) {
        return this.productTraderRepository.findAll(); //ByUser(user);
    }

    @Override
    public void save(ProductTrader productTrader) {

    }
}
