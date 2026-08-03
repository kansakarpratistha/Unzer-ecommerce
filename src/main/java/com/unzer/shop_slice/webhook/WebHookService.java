package com.unzer.shop_slice.webhook;

import java.time.Instant;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unzer.shop_slice.inventory.InventoryRepository;
import com.unzer.shop_slice.order.CheckoutResponse;
import com.unzer.shop_slice.order.Order;
import com.unzer.shop_slice.order.OrderItems;
import com.unzer.shop_slice.order.OrderItemsRepository;
import com.unzer.shop_slice.order.OrderRepository;
import com.unzer.shop_slice.order.OrderStatus;
import com.unzer.shop_slice.payment.PaymentTransaction;
import com.unzer.shop_slice.payment.PaymentTransactionRepository;


@Service
public class WebHookService {
    private final ProcessedWebhookEventRepository processedWebhookEventRepository;
    private final PaymentTransactionRepository paymentTransactionRepository;
    private final OrderRepository orderRepository;
    private final OrderItemsRepository orderItemsRepository;
    private final InventoryRepository inventoryRepository;

    public WebHookService(ProcessedWebhookEventRepository processedWebhookEventRepository,
                          PaymentTransactionRepository paymentTransactionRepository,
                          OrderRepository orderRepository,
                          OrderItemsRepository orderItemsRepository,
                          InventoryRepository inventoryRepository) {
        this.processedWebhookEventRepository = processedWebhookEventRepository;
        this.paymentTransactionRepository = paymentTransactionRepository;
        this.orderRepository = orderRepository;
        this.orderItemsRepository = orderItemsRepository;
        this.inventoryRepository = inventoryRepository;
    }

    @Transactional
    public WebhookResponse handleWebhookEvent(UnzerWebHookPayLoad event) {
        // Check if the event has already been processed
        if (processedWebhookEventRepository.existsById(event.getEventId())) {
            return null; // Event already processed, ignore it
        }

        PaymentTransaction transaction = paymentTransactionRepository.findByUnzerTransactionId(event.getTransactionId())
                .orElseThrow(() -> new RuntimeException("Transaction not found for ID: " + event.getTransactionId()));

        Order order = orderRepository.findById(transaction.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found for ID: " + transaction.getOrderId()));

        List<OrderItems> orderItems = orderItemsRepository.findByOrderId(order.getId());

        if (event.isSuccess()) {
            order.setStatus(OrderStatus.PAID);
            for (OrderItems item : orderItems) {
                inventoryRepository.commitReservation(item.getProductId(), item.getQuantity());
            }
        } else {
            order.setStatus(OrderStatus.PAYMENT_FAILED);
            // Release reserved inventory
            for (OrderItems item : orderItems) {
                inventoryRepository.releaseReservation(item.getProductId(), item.getQuantity());
            }
        }
        order.setUpdatedAt(Instant.now());
        orderRepository.save(order);

        ProcessedWebhookEvent processedEvent = new ProcessedWebhookEvent(event.getEventId(), Instant.now());
        processedWebhookEventRepository.save(processedEvent);

        return new WebhookResponse(order.getId(), order.getStatus(), transaction.getUnzerTransactionId(), transaction.getTransactionStatus().toString());
    }
}
