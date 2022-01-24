package com.example.demo.Utils;

import com.example.demo.dto.OrderDto;
import com.example.demo.dto.ProductProducerDto;
import com.example.demo.dto.ProductTraderDto;
import com.example.demo.dto.ProductTraderUpdateStockDto;
import com.example.demo.model.Order;
import com.example.demo.model.ProductOrder;
import com.example.demo.model.ProductProducer;
import com.example.demo.model.ProductTrader;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

import java.util.List;
import java.util.stream.Collectors;

public class Utils {

    public static ProductProducerDto convertToProductProducerDto(ProductProducer productProducer, ModelMapper modelMapper) {


        TypeMap<ProductProducer, ProductProducerDto> propertyMapper = modelMapper.getTypeMap(ProductProducer.class, ProductProducerDto.class);

        if (propertyMapper == null) {
            propertyMapper = modelMapper.createTypeMap(ProductProducer.class, ProductProducerDto.class);
            propertyMapper.addMappings(mapper -> {
                mapper.map(src -> src.getProducer().getName(), ProductProducerDto::setProducer);
                mapper.map(src -> src.getProduct().getName(), ProductProducerDto::setProductName);
                mapper.map(src -> src.getProduct().getCategory().getName(), ProductProducerDto::setCategory);
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

    public static ProductTraderDto convertToProductTraderUpdateStockDto(ProductTrader productTrader, ModelMapper modelMapper) {

        TypeMap<ProductTrader, ProductTraderUpdateStockDto> propertyMapper = modelMapper.getTypeMap(ProductTrader.class, ProductTraderUpdateStockDto.class);

        if (propertyMapper == null) {
            propertyMapper = modelMapper.createTypeMap(ProductTrader.class, ProductTraderUpdateStockDto.class);
            propertyMapper.addMappings(mapper -> {
                mapper.map(src->src.getId(), ProductTraderUpdateStockDto::setId);
                mapper.map(src -> src.getProductProducer().getProducer().getName(), ProductTraderUpdateStockDto::setProducer);
                mapper.map(src -> src.getProductProducer().getProduct().getName(), ProductTraderUpdateStockDto::setProductName);
                mapper.map(src -> src.getProductProducer().getPrice(), ProductTraderUpdateStockDto::setPrice);
                mapper.map(src -> src.getStock().getQuantity(), ProductTraderUpdateStockDto::setQuantity);
                mapper.map(src -> src.getStock().getMaxQuantity(), ProductTraderUpdateStockDto::setMaxQuantity);
                mapper.map(src -> src.getStock().getMinQuantity(), ProductTraderUpdateStockDto::setMinQuantity);
                mapper.map(src -> src.isSubscription(), ProductTraderUpdateStockDto::setSubscription);
            });
        }

        return modelMapper.map(productTrader, ProductTraderDto.class);
    }

    public static OrderDto convertToOrderDto(Order order, List<ProductOrder> productOrderList, ModelMapper modelMapper) {

        modelMapper = new ModelMapper();

        TypeMap<Order, OrderDto> propertyMapper = modelMapper.getTypeMap(Order.class, OrderDto.class);

        if (propertyMapper == null) {
            propertyMapper = modelMapper.createTypeMap(Order.class, OrderDto.class);

            propertyMapper.addMappings(mapper -> {
                mapper.map(src -> productOrderList
                        .stream()
                        .map(po -> po.getQuantity() * po.getProductTrader().getProductProducer().getPrice()).mapToDouble(Double::doubleValue).sum(), OrderDto::setTotalPrice);
                mapper.map(src -> productOrderList
                        .stream()
                        .collect(Collectors.toMap(o -> o.getProductTrader().getProductProducer().getProduct().getName(), o -> o.getQuantity())), OrderDto::setProductQuantity);
            });

        }

        return modelMapper.map(order, OrderDto.class);
    }
}
