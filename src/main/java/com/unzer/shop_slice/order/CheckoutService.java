package com.unzer.shop_slice.order;

import java.math.BigDecimal;
import java.time.Instant;

import org.springframework.stereotype.Service;

import com.unzer.shop_slice.inventory.InventoryRepository;
import com.unzer.shop_slice.payment.PaymentInitResult;
import com.unzer.shop_slice.payment.PaymentMethod;
import com.unzer.shop_slice.payment.PaymentService;
import com.unzer.shop_slice.products.ProductRepository;

import jakarta.transaction.Transactional;

@Service
public class CheckoutService {
    private final OrderRepository orderRepository;
    private final OrderItemsRepository orderItemsRepository;
    private final InventoryRepository inventoryRepository;
    private final ProductRepository productRepository;
    private final PaymentService paymentService;

    public CheckoutService(OrderRepository orderRepository, OrderItemsRepository orderItemsRepository,
                           InventoryRepository inventoryRepository, ProductRepository productRepository, PaymentService paymentService) {
        this.orderRepository = orderRepository;
        this.orderItemsRepository = orderItemsRepository;
        this.inventoryRepository = inventoryRepository;
        this.productRepository = productRepository;
        this.paymentService = paymentService;
    }

    @Transactional
    public CheckoutResponse checkout(CheckoutRequest request) {
        // Create a new order
        BigDecimal total = BigDecimal.ZERO;
        Order order = new Order(null, null, request.getGuestEmail(), OrderStatus.CREATED, total, Instant.now(), Instant.now());
        orderRepository.save(order);

        // Reserve inventory for each item
        for (CheckoutRequest.Item item : request.getItems()) {
            boolean reserved = inventoryRepository.tryReserve(item.getProductId(), item.getQuantity());
            if (!reserved) {
                throw new RuntimeException("Insufficient inventory for product ID: " + item.getProductId());
            }

            // Create order items
            OrderItems orderItem = new OrderItems();
            orderItem.setOrderId(order.getId());
            orderItem.setProductId(item.getProductId());
            orderItem.setQuantity(item.getQuantity());
            orderItemsRepository.save(orderItem);

            total = total.add(productRepository.findById(item.getProductId()).get().getUnitPrice().multiply(new BigDecimal(item.getQuantity())));
        }

        order.setTotalAmount(total);
        order.setStatus(OrderStatus.AWAITING_PAYMENT);
        order.setUpdatedAt(Instant.now());
        orderRepository.save(order);

        PaymentInitResult paymentResult = paymentService.charge(order, PaymentMethod.CREDIT_CARD, request.getCardToken());

        

        return new CheckoutResponse(order.getId(), order.getStatus(), paymentResult.getRedirectUrl());
    }
}
