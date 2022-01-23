package com.example.demo.service.Impl;

import com.example.demo.dao.OrderRepository;
import com.example.demo.dao.ProductOrderRepository;
import com.example.demo.model.Order;
import com.example.demo.model.ProductOrder;
import com.example.demo.model.User;
import com.example.demo.service.OrderService;
import com.example.demo.service.ProductOrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductOrderServiceImpl implements ProductOrderService {

    private final ProductOrderRepository productOrderRepository;

    public ProductOrderServiceImpl(ProductOrderRepository productOrderRepository) {
        this.productOrderRepository = productOrderRepository;
    }

    @Override
    public List<ProductOrder> getAllOrdersByOrder(Order order) {
        return productOrderRepository.findAllByOrder(order);
    }
}
