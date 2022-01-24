package com.example.demo.service;

import com.example.demo.dto.ProductProducerDto;
import com.example.demo.model.Product;
import com.example.demo.model.ProductProducer;
import com.example.demo.model.ProductTrader;
import com.example.demo.model.User;

import java.util.List;

public interface ProductProducerService {

    public List<ProductProducer> getAllProducerProducts();

    public ProductProducer getProductProducerById(Long id);

    public List<ProductProducer> getProductProducerByProducer (User user);

    public List<ProductProducer> getAllProductProducerByProduct(Product product);

}
