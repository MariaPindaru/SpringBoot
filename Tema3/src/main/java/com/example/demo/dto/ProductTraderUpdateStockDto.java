package com.example.demo.dto;

public class ProductTraderUpdateStockDto {
    private Long id;
    private String producer;
    private String productName;
    private Double price;
    private Double quantity;
    private Double maxQuantity;
    private Double minQuantity;

    public ProductTraderUpdateStockDto() {

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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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
