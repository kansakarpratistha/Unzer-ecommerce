package com.unzer.shop_slice.payment;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Currency;
import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.unzer.payment.Charge;
import com.unzer.payment.Unzer;
import com.unzer.payment.communication.HttpCommunicationException;
import com.unzer.payment.paymenttypes.Card;
import com.unzer.payment.paymenttypes.Wero;
import com.unzer.shop_slice.order.Order;

@Component
@Profile("unzer")
public class WeroPaymentHandler implements PaymentMethodHandler {
    private final Unzer unzer;

    public WeroPaymentHandler(Unzer unzer) {
        this.unzer = unzer;
    }

    public String createWeroPayment() {
        Wero wero = new Wero();
        wero = unzer.createPaymentType(wero);
        return wero.getId();
    }

    @Override
    public PaymentInitResult initiatePayment(Order order, Optional<String> cardToken) {
        Card weroPaymentType = (Card) unzer.fetchPaymentType(createWeroPayment());
        try {
            Charge charge = unzer.charge(
                order.getTotalAmount(), 
                Currency.getInstance(order.getCurrency()), 
                weroPaymentType, 
                new URL("returnUrl")
            );
            return new PaymentInitResult(
                charge.getId(), 
                charge.getPaymentId(), 
                TransactionStatus.SUCCESS, 
                charge.getRedirectUrl().toString());
        } catch (HttpCommunicationException e) {
            throw new RuntimeException("Wero Payment failed", e);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Wero Payment failed", e);
        }

        
    }

    @Override
    public PaymentMethod getPaymentMethod() {
        return PaymentMethod.WERO;
    }

    
}
