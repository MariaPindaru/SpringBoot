package com.example.demo.Utils;

import com.example.demo.dto.OrderDto;
import com.example.demo.dto.ProductProducerDto;
import com.example.demo.dto.ProductTraderDto;
import com.example.demo.model.Order;
import com.example.demo.model.ProductOrder;
import com.example.demo.model.ProductProducer;
import com.example.demo.model.ProductTrader;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public static OrderDto convertToOrderDto(Order order, List<ProductOrder> productOrderList, ModelMapper modelMapper) {

        Double totalPrice = productOrderList
                .stream()
                .map(po -> po.getQuantity() * po.getProductTrader().getProductProducer().getPrice()).mapToDouble(Double::doubleValue).sum();

        Map<String, Long> productQuantityMap =
                productOrderList
                        .stream()
                        .collect(Collectors.toMap(o -> o.getProductTrader().getProductProducer().getProduct().getName(), o-> o.getQuantity()));

        TypeMap<Order, OrderDto> propertyMapper = modelMapper.getTypeMap(Order.class, OrderDto.class);

        if (propertyMapper == null) {
            propertyMapper = modelMapper.createTypeMap(Order.class, OrderDto.class);

            propertyMapper.addMappings(mapper -> {
                mapper.map(src -> totalPrice, OrderDto::setTotalPrice);
                mapper.map(src -> productQuantityMap, OrderDto::setProductQuantity);
            });
        }

        return modelMapper.map(order, OrderDto.class);
    }
}