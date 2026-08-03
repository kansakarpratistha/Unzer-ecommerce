package com.unzer.shop_slice.webhook;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcessedWebhookEventRepository extends JpaRepository<ProcessedWebhookEvent, String> {
    
}
