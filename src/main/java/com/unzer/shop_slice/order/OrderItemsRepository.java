package com.unzer.shop_slice.order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemsRepository extends JpaRepository<OrderItems, Long> {

    List<OrderItems> findByOrderId(Long id);
    
}
