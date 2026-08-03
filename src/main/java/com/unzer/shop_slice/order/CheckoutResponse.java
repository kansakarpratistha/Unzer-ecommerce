package com.unzer.shop_slice.order;

public class CheckoutResponse {
    private Long orderId;
    private OrderStatus status;
    private String redirectUrl;

    public CheckoutResponse(Long orderId, OrderStatus status, String redirectUrl) {
        this.orderId = orderId;
        this.status = status;
        this.redirectUrl = redirectUrl;
    }

    public Long getOrderId() {
        return orderId;
    }
    public OrderStatus getStatus() {
        return status;
    }
    public String getRedirectUrl() {
        return redirectUrl;
    }
}
