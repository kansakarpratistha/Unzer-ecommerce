package com.unzer.shop_slice.payment;

import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.unzer.shop_slice.order.Order;

@Component
@Profile("mock-payment")
public class MockCardPaymentHandler implements PaymentMethodHandler {

    @Override
    public PaymentMethod getPaymentMethod() {
        return PaymentMethod.CREDIT_CARD;
    }

    @Override
    public PaymentInitResult initiatePayment(Order order, Optional<String> cardToken) {

        return new PaymentInitResult(
                "mock-resource-id",
                "mock-payment-id",
                TransactionStatus.SUCCESS,
                null
        );
    }
}