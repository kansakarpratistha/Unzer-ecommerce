package com.unzer.shop_slice.payment;

public class PaymentInitResult {
    private String unzerResourceId;
    private String unzerTransactionId;
    private TransactionStatus transactionStatus;
    private String redirectUrl;

    public PaymentInitResult(String unzerResourceId, String unzerTransactionId, TransactionStatus transactionStatus, String redirectUrl) {
        this.unzerResourceId = unzerResourceId;
        this.unzerTransactionId = unzerTransactionId;
        this.transactionStatus = transactionStatus;
        this.redirectUrl = redirectUrl;
    }

    // Getters and Setters
    public String getUnzerResourceId() {
        return unzerResourceId;
    }

    public void setUnzerResourceId(String unzerResourceId) {
        this.unzerResourceId = unzerResourceId;
    }

    public String getUnzerTransactionId() {
        return unzerTransactionId;
    }

    public void setUnzerTransactionId(String unzerTransactionId) {
        this.unzerTransactionId = unzerTransactionId;
    }

    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }
}
