package com.unzer.shop_slice.payment;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Currency;
import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.unzer.payment.Authorization;
import com.unzer.payment.Charge;
import com.unzer.payment.Unzer;
import com.unzer.payment.communication.HttpCommunicationException;
import com.unzer.shop_slice.order.Order;

@Component
@Profile("unzer")
public class CardPaymentHandler implements PaymentMethodHandler {

    private final Unzer unzer;

    public CardPaymentHandler(Unzer unzer) {
        this.unzer = unzer;
    }
    
    @Override
    public PaymentInitResult initiatePayment(Order order, Optional<String> cardToken) {
        Charge charge;
        try {
            charge = unzer.charge(
                order.getTotalAmount(),
                Currency.getInstance(order.getCurrency()),
                Optional.of(cardToken).toString(),
                new URL(
                    "https://your-redirect-url.com"
                )
            );
            return new PaymentInitResult(
            charge.getId(),
            charge.getPaymentId(),
            TransactionStatus.SUCCESS, 
            charge.getRedirectUrl().toString());
        } catch (Exception e) {
            throw new RuntimeException("Card payment failed", e);
        } 

        
    }
        @Override
        public PaymentMethod getPaymentMethod() {
            return PaymentMethod.CREDIT_CARD;
        }
}
