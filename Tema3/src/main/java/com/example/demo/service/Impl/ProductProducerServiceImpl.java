package com.example.demo.service.Impl;

import com.example.demo.dao.ProductProducerRepository;
import com.example.demo.dto.ProductProducerDto;
import com.example.demo.model.ProductProducer;
import com.example.demo.model.ProductTrader;
import com.example.demo.service.ProductProducerService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public ProductProducer getProductProducerById(Long id){

        return productProducerRepository.findProductProducerById(id);
    }
}
