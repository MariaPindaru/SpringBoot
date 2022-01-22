package com.example.demo.dto;


import com.example.demo.model.Product;
import com.example.demo.model.User;

public class ProductTraderDto {

    private Long id;
    private String producer;
    private String trader;
    private String productName;
    private Double price;
    private Double quantity;
    private Double maxQuantity;
    private Double minQuantity;

    public ProductTraderDto() {
    }

    public ProductTraderDto(Long id, String producer, String trader, String product, Double price, Double quantity, Double maxQuantity, Double minQuantity) {
        this.id = id;
        this.producer = producer;
        this.trader = trader;
        this.productName = product;
        this.price = price;
        this.quantity = quantity;
        this.maxQuantity = maxQuantity;
        this.minQuantity = minQuantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getTrader() {
        return trader;
    }

    public void setTrader(String trader) {
        this.trader = trader;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String product) {
        this.productName = product;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getMaxQuantity() {
        return maxQuantity;
    }

    public void setMaxQuantity(Double maxQuantity) {
        this.maxQuantity = maxQuantity;
    }

    public Double getMinQuantity() {
        return minQuantity;
    }

    public void setMinQuantity(Double minQuantity) {
        this.minQuantity = minQuantity;
    }
}
