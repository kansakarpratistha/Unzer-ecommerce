package com.unzer.shop_slice.payment;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentTransactionRepository extends JpaRepository<PaymentTransaction, Long> {
    Optional<PaymentTransaction> findByUnzerTransactionId(String unzerTransactionId);
}
