package com.example.demo.service;

import com.example.demo.dto.ProductProducerDto;
import com.example.demo.model.ProductProducer;
import com.example.demo.model.ProductTrader;

import java.util.List;

public interface ProductProducerService {
    public List<ProductProducer> getAllProducerProducts();

    public ProductProducer getProductProducerById(Long id);

    //public void save(ProductTrader productTrader);
}
