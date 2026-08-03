package com.unzer.shop_slice.webhook;

import com.unzer.shop_slice.order.OrderStatus;

public class WebhookResponse {
    private Long orderId;
    private OrderStatus status;
    private String paymentTransactionId;
    private String paymentStatus;

    public WebhookResponse(Long orderId, OrderStatus status, String paymentTransactionId, String paymentStatus) {
        this.orderId = orderId;
        this.status = status;
        this.paymentTransactionId = paymentTransactionId;
        this.paymentStatus = paymentStatus;
    }

    public Long getOrderId() {
        return orderId;
    }
    public OrderStatus getStatus() {
        return status;
    }
    public String getPaymentTransactionId(){
        return paymentTransactionId;
    }
    public String getPaymentStatus(){
        return paymentStatus;
    }
}
