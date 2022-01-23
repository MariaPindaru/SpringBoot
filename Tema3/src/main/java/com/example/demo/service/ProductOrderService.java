package com.example.demo.service;

import com.example.demo.model.Order;
import com.example.demo.model.ProductOrder;

import java.util.List;

public interface ProductOrderService {

    public List<ProductOrder> getAllOrdersByOrder(Order order);
}
