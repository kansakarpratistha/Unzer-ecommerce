package com.unzer.shop_slice.order;

import java.math.BigDecimal;
import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long customerId;
    private String guestEmail;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private BigDecimal totalAmount;
    private String currency;
    
    //Shipping address fields
    // private String shippingHouseNumber;
    // private String shippingStreet;
    // private String shippingPostalCode;
    // private String shippingState;
    // private String shippingCountry;

    private Instant createdAt;
    private Instant updatedAt;

    protected Order() {}

    public Order(Long id, Long customerId, String guestEmail, OrderStatus status, BigDecimal totalAmount, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.customerId = customerId;
        this.guestEmail = guestEmail;
        this.status = status;
        this.totalAmount = totalAmount;
        this.currency = currency;
        // this.shippingHouseNumber = shippingHouseNumber;
        // this.shippingStreet = shippingStreet;
        // this.shippingPostalCode = shippingPostalCode;
        // this.shippingState = shippingState;
        // this.shippingCountry = shippingCountry;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
    public String getGuestEmail() {
        return guestEmail;
    }
    public void setGuestEmail(String guestEmail) {
        this.guestEmail = guestEmail;
    }
    public OrderStatus getStatus() {
        return status;
    }
    public void setStatus(OrderStatus status) {
        this.status = status;
    }
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
    public String getCurrency() {
        return currency;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    // public String getShippingHouseNumber() {
    //     return shippingHouseNumber;
    // }
    // public void setShippingHouseNumber(String shippingHouseNumber) {
    //     this.shippingHouseNumber = shippingHouseNumber;
    // }
    // public String getShippingStreet() {
    //     return shippingStreet;
    // }
    // public void setShippingStreet(String shippingStreet) {
    //     this.shippingStreet = shippingStreet;
    // }
    // public String getShippingPostalCode() {
    //     return shippingPostalCode;
    // }
    // public void setShippingPostalCode(String shippingPostalCode) {
    //     this.shippingPostalCode = shippingPostalCode;
    // }
    // public String getShippingState() {
    //     return shippingState;
    // }
    // public void setShippingState(String shippingState) {
    //     this.shippingState = shippingState;
    // }
    // public String getShippingCountry() {
    //     return shippingCountry;
    // }

    // public void setShippingCountry(String shippingCountry) {
    //     this.shippingCountry = shippingCountry;
    // }
    public Instant getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
    public Instant getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

}
