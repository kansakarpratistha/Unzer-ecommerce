package com.unzer.shop_slice.order;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "order_items")
public class OrderItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long orderId;
    private Long productId;
    private int quantity;
    private BigDecimal unitPriceAtSale;
    private String currency;

    protected OrderItems() {}

    public OrderItems(Long id, Long orderId, Long productId, int quantity, BigDecimal unitPriceAtSale, String currency) {
        this.id = id;
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.unitPriceAtSale = unitPriceAtSale;
        this.currency = currency;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getProductId() {
        return productId;
    }
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPriceAtSale() {
        return unitPriceAtSale;
    }
    public void setUnitPriceAtSale(BigDecimal unitPriceAtSale) {
        this.unitPriceAtSale = unitPriceAtSale;
    }

    public String getCurrency() {
        return currency;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
