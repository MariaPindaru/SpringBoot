package com.example.demo.dao.repository;

import com.example.demo.model.ProductProducer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductProducerRepository extends JpaRepository<ProductProducer, Long> {
}