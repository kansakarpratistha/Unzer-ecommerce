# Checkout and Payment Vertical Code Slice

## Overview

This project implementes the vertical slice of the checkout process for an e-commerce application integrating Unzer Payment Subsystem. The implemented flow covers the complete path from checkout initiation to successful payment confirmation and order, inventory update.

## Features Implemented
* Checkout endpoint 
* Order Creation on Checkout
* Inventory reservation on checkout and before payment
* Order Lifecycle: CREATED -> AWAITING_PAYMENT -> PAID / PAYMENT_FAILED
        (Other Order statuses: FULFILLING -> SHIPPED -> CANCELLED -> REFUNDED )
* Payment Abstraction (PaymentHandler)
* Unzer Payment implementation - Credit Card and WERO 
* Webhook endpoint and idempotency
* Inventory reservation commit on successful payment
* Inventory reservation release on failed payment

## Prerequisites
* Java 17
* Maven

## Configuration

**Without Unzer API Key**
```
    profiles:
        active: mock-payment
```

**With Unzer API Key**
```
    profiles:
        active: unzer
    unzer:
        private-key: ${UNZER_PRIVATE_KEY}
```

Supply the Unzer credentials externally or via environment variables.


## Running the Application

```
    mvn clean install
```

or

```
    mvn soring-boot:run
```

## Checkout API Testing
Powershell:

```
    Invoke-RestMethod -Uri "http://localhost:8080/checkout" `                               
    -Method Post `                                
    -ContentType "application/json" `                                                                               
    -Body '{
        "guestEmail":"test@example.com",
        "items":[{
            "productId":1,
            "qty":1
            }],
        "paymentTypeId":"crd",
        "cardToken":"s-crd-xxxxxxxxx"
    }'                                                                        
```

## Webhook Testing

Powershell:

```
    Invoke-RestMethod `                                                                     
    -Uri "http://localhost:8080/payment/webhook" `
    -Method POST `                                                                   
    -ContentType "application/json" `                                          
    -Body '{
        "eventId": "evt-001",
        "transactionId": "mock-payment-id",
        "status": "SUCCESS"
    }'
```