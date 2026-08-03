package com.unzer.shop_slice.products;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // Additional query methods can be defined here if needed
    
}
