package com.example.demo.service.Impl;

import com.example.demo.dao.repository.ProductProducerRepository;
import com.example.demo.model.ProductProducer;
import com.example.demo.model.ProductTrader;
import com.example.demo.service.ProductProducerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductProducerServiceImpl implements ProductProducerService {

    private final ProductProducerRepository productProducerRepository;

    public ProductProducerServiceImpl(ProductProducerRepository productProducerRepository) {
        this.productProducerRepository = productProducerRepository;
    }

    @Override
    public List<ProductProducer> getAllProducerProducts() {
        return productProducerRepository.findAll();
    }
}
