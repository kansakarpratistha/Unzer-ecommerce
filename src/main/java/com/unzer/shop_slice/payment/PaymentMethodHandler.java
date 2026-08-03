package com.unzer.shop_slice.payment;

import java.util.Optional;

import com.unzer.shop_slice.order.Order;

public interface PaymentMethodHandler {
    PaymentInitResult initiatePayment(Order order, Optional<String> cardToken);
    PaymentMethod getPaymentMethod();
}
