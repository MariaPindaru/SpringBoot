package com.example.demo.dao;

import com.example.demo.model.Product;
import com.example.demo.model.ProductProducer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductProducerRepository extends JpaRepository<ProductProducer, Long> {
    ProductProducer findProductProducerById(Long id);

    List<ProductProducer> findProductProducerByProduct(Product product);
}