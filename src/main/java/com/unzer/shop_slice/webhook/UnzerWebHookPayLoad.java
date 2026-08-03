package com.unzer.shop_slice.webhook;

public class UnzerWebHookPayLoad {
    private String eventId;
    private String transactionId;
    private String status;

    public String getEventId() {
        return eventId;
    }
    public void setEventId(String eventId) {
        this.eventId = eventId;
    }
    public String getTransactionId() {
        return transactionId;
    }
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isSuccess(){
        return "SUCCESS".equalsIgnoreCase(status);
    }
}
