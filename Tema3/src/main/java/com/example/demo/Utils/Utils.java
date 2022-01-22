package com.example.demo.Utils;

import com.example.demo.dto.ProductProducerDto;
import com.example.demo.dto.ProductTraderDto;
import com.example.demo.model.ProductProducer;
import com.example.demo.model.ProductTrader;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

public class Utils {

    public static ProductProducerDto convertToProductProducerDto(ProductProducer productProducer, ModelMapper modelMapper) {

        TypeMap<ProductProducer, ProductProducerDto> propertyMapper = modelMapper.getTypeMap(ProductProducer.class, ProductProducerDto.class);

        if (propertyMapper == null) {
            propertyMapper = modelMapper.createTypeMap(ProductProducer.class, ProductProducerDto.class);
            propertyMapper.addMappings(mapper -> {
                mapper.map(src -> src.getProducer().getName(), ProductProducerDto::setProducer);
                mapper.map(src -> src.getProduct().getName(), ProductProducerDto::setProductName);
            });
        }

        return modelMapper.map(productProducer, ProductProducerDto.class);
    }

    public static ProductTraderDto convertToProductTraderDto(ProductTrader productTrader, ModelMapper modelMapper) {

        TypeMap<ProductTrader, ProductTraderDto> propertyMapper = modelMapper.getTypeMap(ProductTrader.class, ProductTraderDto.class);

        if (propertyMapper == null) {
            propertyMapper = modelMapper.createTypeMap(ProductTrader.class, ProductTraderDto.class);
            propertyMapper.addMappings(mapper -> {
                mapper.map(src -> src.getTrader().getName(), ProductTraderDto::setTrader);
                mapper.map(src -> src.getProductProducer().getProducer().getName(), ProductTraderDto::setProducer);
                mapper.map(src -> src.getProductProducer().getProduct().getName(), ProductTraderDto::setProductName);
                mapper.map(src -> src.getProductProducer().getPrice(), ProductTraderDto::setPrice);
                mapper.map(src -> src.getStock().getQuantity(), ProductTraderDto::setQuantity);
                mapper.map(src -> src.getStock().getMaxQuantity(), ProductTraderDto::setMaxQuantity);
                mapper.map(src -> src.getStock().getMinQuantity(), ProductTraderDto::setMinQuantity);
            });
        }

        return modelMapper.map(productTrader, ProductTraderDto.class);
    }
}
