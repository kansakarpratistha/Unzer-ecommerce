package com.unzer.shop_slice.payment;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.unzer.shop_slice.order.Order;

@Service
public class PaymentService {
    private final Map<PaymentMethod, PaymentMethodHandler> paymentHandlers;
    private final PaymentTransactionRepository paymentTransactionRepository;

    public PaymentService(List<PaymentMethodHandler> handlersList, PaymentTransactionRepository paymentTransactionRepository) {
        this.paymentHandlers = handlersList.stream().collect(Collectors.toMap(PaymentMethodHandler::getPaymentMethod, handler -> handler));
        this.paymentTransactionRepository = paymentTransactionRepository;
    }

    public PaymentInitResult charge(Order order, PaymentMethod paymentMethod, String cardToken) {
        PaymentMethodHandler handler = paymentHandlers.get(paymentMethod);
        if (handler == null) {
            throw new IllegalArgumentException("Unsupported payment method: " + paymentMethod);
        }

        PaymentInitResult result = handler.initiatePayment(order, Optional.of(cardToken));
        

        // Save the transaction details to the database
        PaymentTransaction transaction = new PaymentTransaction(
            null,
            order.getId(),
            result.getUnzerResourceId(),
            result.getUnzerTransactionId(),
            paymentMethod,
            TransactionType.CHARGE,
            result.getTransactionStatus(),
            order.getTotalAmount(),
            order.getCurrency(),
            Instant.now().toString()
        );
        paymentTransactionRepository.save(transaction);

        return result;
    }
}
