package com.example.demo.dto;

public class ProductTraderCreationDto {
    private Long productProducerId;
    private Long minQuantity;
    private Long maxQuantity;
    private Long buyQuantity;

    public ProductTraderCreationDto() {
    }

    public Long getProductProducerId() {
        return productProducerId;
    }

    public void setProductProducerId(Long productProducerId) {
        this.productProducerId = productProducerId;
    }

    public Long getMinQuantity() {
        return minQuantity;
    }

    public void setMinQuantity(Long minQuantity) {
        this.minQuantity = minQuantity;
    }

    public Long getMaxQuantity() {
        return maxQuantity;
    }

    public void setMaxQuantity(Long maxQuantity) {
        this.maxQuantity = maxQuantity;
    }

    public Long getBuyQuantity() {
        return buyQuantity;
    }

    public void setBuyQuantity(Long buyQuantity) {
        this.buyQuantity = buyQuantity;
    }
}
