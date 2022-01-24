package com.example.demo.dao;

import com.example.demo.model.ProductTrader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductTraderRepository extends JpaRepository<ProductTrader, Long> {

    @Query("SELECT t FROM ProductTrader t WHERE t.trader.id = ?1")
    List<ProductTrader> findAllByUserId(Long id);

   Optional<ProductTrader> findById(Long id);

    @Query("SELECT t FROM ProductTrader t WHERE t.trader.name = ?1 AND t.productProducer.product.name = ?2" )
    ProductTrader findByTraderNameAndProductName(String trader, String product);
}
