package com.example.demo.dao.repository;

import com.example.demo.model.ProductTrader;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductTraderRepository extends JpaRepository<ProductTrader, Long> {

    @Query("SELECT t FROM ProductTrader t WHERE t.trader.id = ?1")
    List<ProductTrader> findAllByUserId(Long id);
}
