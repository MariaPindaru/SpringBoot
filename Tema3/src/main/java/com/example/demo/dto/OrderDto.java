package com.example.demo.dto;

import java.util.Date;
import java.util.Map;

public class OrderDto {
    private Long orderId;
    private Date date;
    private Double totalPrice;
    private Map<String, Long> productQuantity;

    public OrderDto() {
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Map<String, Long> getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Map<String, Long> productQuantity) {
        this.productQuantity = productQuantity;
    }
}
