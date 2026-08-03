package com.unzer.shop_slice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Instant;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.unzer.payment.Unzer;
import com.unzer.shop_slice.inventory.Inventory;
import com.unzer.shop_slice.inventory.InventoryRepository;
import com.unzer.shop_slice.payment.CardPaymentHandler;

@Disabled("Temporarily skipped")
@SpringBootTest
@Import(InventoryRepository.class)
public class InventoryConcurrencyTest {

    private InventoryRepository inventoryRepository;
    
    @Autowired
    InventoryConcurrencyTest(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Test
    void onlyOneReservationShouldSucceed() throws InterruptedException {
        // This test should simulate concurrent reservations and assert that only one succeeds.
        // You can use threads or an executor service to simulate concurrency.
        Long productId = 10L; // Assuming this product exists in the inventory
        int availableQuantity = 1;
        inventoryRepository.insert(productId, availableQuantity, 0, Instant.now(), Instant.now());

        int tryQuantity = 1; // Each thread will try to reserve 1 item
        int threadCount = 10; // Number of concurrent threads trying to reserve
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        CountDownLatch latch = new CountDownLatch(1);
        CountDownLatch doneLatch = new CountDownLatch(threadCount);
        AtomicInteger successCount = new AtomicInteger(0);

        for (int i = 0; i < threadCount; i++) {
            executorService.submit(() -> {
                try {
                    latch.await(); // Wait for the signal to start
                    boolean success = inventoryRepository.tryReserve(productId, tryQuantity);
                    if (success) {
                        successCount.incrementAndGet();
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    doneLatch.countDown();
                }
            });
        }

        latch.countDown(); 
        doneLatch.await(5, TimeUnit.SECONDS);
        executorService.shutdown();

        assertEquals(1, successCount.get(), "Only one reservation should succeed");

        Inventory inventory = inventoryRepository.findById(productId);
        if (inventory == null) {
            throw new RuntimeException("Inventory not found");
        }
        assertEquals(1, inventory.getReservedQuantity(), "Reserved quantity should be 1 after one successful reservation");
    }
}
