package com.example.demo.service.Impl;

import com.example.demo.dao.ProductProducerRepository;
import com.example.demo.dto.ProductProducerDto;
import com.example.demo.model.ProductProducer;
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

    @Autowired
    private ModelMapper modelMapper;

    public ProductProducerServiceImpl(ProductProducerRepository productProducerRepository) {
        this.productProducerRepository = productProducerRepository;
    }

    @Override
    public List<ProductProducer> getAllProducerProducts() {

        return productProducerRepository.findAll();
    }

//    private ProductProducerDto convertToDto(ProductProducer productProducer) {
//
//        TypeMap<ProductProducer, ProductProducerDto> propertyMapper = this.modelMapper.getTypeMap(ProductProducer.class, ProductProducerDto.class);
//
//        if(productProducer == null)
//
//        propertyMapper.addMappings( mapper -> {
////            mapper.map(src -> src.getId(), ProductProducerDto::setId);
//            mapper.map(src -> src.getProducer().getName(), ProductProducerDto::setProducer);
//            mapper.map(src -> src.getProduct().getName(), ProductProducerDto::setProductName);
////            mapper.map(src -> src.getPrice(), ProductProducerDto::setPrice);
//        } );
//
//        ProductProducerDto pojoObject = modelMapper.map(productProducer, ProductProducerDto.class);
//
//        return pojoObject;
//    }
}
