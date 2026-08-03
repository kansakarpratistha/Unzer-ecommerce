package com.unzer.shop_slice.order;

public class InsufficientStockException extends RuntimeException {
    public InsufficientStockException(Long productId) {
        super("Insufficient stock for product " + productId);
    }
}
