package com.unzer.shop_slice.webhook;

import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "processed_webhook_events")
public class ProcessedWebhookEvent {
    @Id private String eventId;
    private Instant processedAt;

    protected ProcessedWebhookEvent() {}

    public ProcessedWebhookEvent(String eventId, Instant processedAt) {
        this.eventId = eventId;
        this.processedAt = processedAt;
    }

    // Getters and Setters
    public String getEventId() {
        return eventId;
    }
    public void setEventId(String eventId) {
        this.eventId = eventId;
    }
    public Instant getProcessedAt() {
        return processedAt;
    }
    public void setProcessedAt(Instant processedAt) {
        this.processedAt = processedAt;
    }
    
}
