package com.example.demo.dto;

public class ProductTraderCreationDto {
    private Long productProducerId;
    private Double minQuantity;
    private Double maxQuantity;
    private Double buyQuantity;

    public ProductTraderCreationDto() {
    }

    public Long getProductProducerId() {
        return productProducerId;
    }

    public void setProductProducerId(Long productProducerId) {
        this.productProducerId = productProducerId;
    }

    public Double getMinQuantity() {
        return minQuantity;
    }

    public void setMinQuantity(Double minQuantity) {
        this.minQuantity = minQuantity;
    }

    public Double getMaxQuantity() {
        return maxQuantity;
    }

    public void setMaxQuantity(Double maxQuantity) {
        this.maxQuantity = maxQuantity;
    }

    public Double getBuyQuantity() {
        return buyQuantity;
    }

    public void setBuyQuantity(Double buyQuantity) {
        this.buyQuantity = buyQuantity;
    }
}
