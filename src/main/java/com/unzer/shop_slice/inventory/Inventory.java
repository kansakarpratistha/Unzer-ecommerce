package com.unzer.shop_slice.inventory;

import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "inventory")
public class Inventory {
    @Id private Long productId;
    private int availableQuantity;
    private int reservedQuantity;
    private Instant createdAt;
    private Instant lastUpdated;

    protected Inventory() {}

    public Inventory(Long productId, int availableQuantity, int reservedQuantity, Instant createdAt, Instant lastUpdated) {
        this.productId = productId;
        this.availableQuantity = availableQuantity;
        this.reservedQuantity = reservedQuantity;
        this.createdAt = createdAt;
        this.lastUpdated = lastUpdated;
    }

    // Getters and Setters
    public Long getProductId() {
        return productId;
    }
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }
    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public int getReservedQuantity() {
        return reservedQuantity;
    }
    public void setReservedQuantity(int reservedQuantity) {
        this.reservedQuantity = reservedQuantity;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getLastUpdated() {
        return lastUpdated;
    }
    public void setLastUpdated(Instant lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

}
