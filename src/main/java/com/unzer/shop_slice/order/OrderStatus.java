package com.unzer.shop_slice.order;

public enum OrderStatus {
    CREATED,
    AWAITING_PAYMENT,
    PAID,
    FULFILLING,
    SHIPPED,
    CANCELLED,
    PAYMENT_FAILED,
    REFUNDED
}
