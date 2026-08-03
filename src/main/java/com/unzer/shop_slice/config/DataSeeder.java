package com.unzer.shop_slice.config;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.unzer.shop_slice.inventory.InventoryRepository;
import com.unzer.shop_slice.products.ProductRepository;
import com.unzer.shop_slice.products.Product;
import com.unzer.shop_slice.inventory.Inventory;

@Component
public class DataSeeder implements CommandLineRunner{
    private final ProductRepository productRepository;
    private final InventoryRepository inventoryRepository;

    public DataSeeder(ProductRepository productRepository, InventoryRepository inventoryRepository) {
        this.productRepository = productRepository;
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Product cup = new Product(null, "Cup", "A simple cup", 1L, new BigDecimal("4.99"), "EUR");
        productRepository.save(cup);
        Product plate = new Product(null, "Plate", "A simple plate", 1L, new BigDecimal("5.99"), "EUR");
        productRepository.save(plate);
        Product bowl = new Product(null, "Bowl", "A simple bowl", 1L, new BigDecimal("5.99"), "EUR");
        productRepository.save(bowl);

        inventoryRepository.insert(cup.getId(), 15, 2, java.time.Instant.now(), java.time.Instant.now());
        inventoryRepository.insert(plate.getId(), 20, 4, java.time.Instant.now(), java.time.Instant.now());
        inventoryRepository.insert(bowl.getId(), 13, 2, java.time.Instant.now(), java.time.Instant.now());
    }
}
