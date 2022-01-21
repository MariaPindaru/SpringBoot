package com.example.demo.dao.repository;

import com.example.demo.model.ProductTrader;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductTraderRepository extends JpaRepository<ProductTrader, Long> {
   // List<ProductTrader> findAllByUser(User user);
}
