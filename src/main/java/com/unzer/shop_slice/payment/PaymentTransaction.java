package com.unzer.shop_slice.payment;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "payment_transactions")
public class PaymentTransaction {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long orderId;

    private String unzerResourceId;
    private String unzerTransactionId;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;

    private BigDecimal amount;
    private String currency;
    private String createdAt;


    protected PaymentTransaction() {}

    public PaymentTransaction(Long id, Long orderId, String unzerResourceId, String unzerTransactionId,
                              PaymentMethod paymentMethod, TransactionType transactionType,
                              TransactionStatus transactionStatus, BigDecimal amount, String currency,
                              String createdAt) {
        this.id = id;
        this.orderId = orderId;
        this.unzerResourceId = unzerResourceId;
        this.unzerTransactionId = unzerTransactionId;
        this.paymentMethod = paymentMethod;
        this.transactionType = transactionType;
        this.transactionStatus = transactionStatus;
        this.amount = amount;
        this.currency = currency;
        this.createdAt = createdAt;
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
    public String getUnzerResourceId() {
        return unzerResourceId;
    }
    public void setUnzerResourceId(String unzerResourceId) {
        this.unzerResourceId = unzerResourceId;
    }
    public String getUnzerTransactionId() {
        return unzerTransactionId;
    }
    public void setUnzerTransactionId(String unzerTransactionId) {
        this.unzerTransactionId = unzerTransactionId;
    }
    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }
    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    public TransactionType getTransactionType() {
        return transactionType;
    }
    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }
    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }
    public void setTransactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }
    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    public String getCurrency() {
        return currency;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    public String getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
    
}
