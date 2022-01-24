package com.example.demo.service;

import com.example.demo.model.Order;
import com.example.demo.model.User;

import java.util.List;

public interface OrderService {

    public List<Order> getAllOrdersByUser(User user);

    void save(Order order);
}
