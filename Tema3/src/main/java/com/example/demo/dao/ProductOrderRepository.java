package com.example.demo.dao;

import com.example.demo.model.Order;
import com.example.demo.model.ProductOrder;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductOrderRepository extends JpaRepository<ProductOrder, Long> {

    List<ProductOrder> findAllByOrder(Order order);
}