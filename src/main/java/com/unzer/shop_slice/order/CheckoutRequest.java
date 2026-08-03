package com.unzer.shop_slice.order;

import java.util.List;

public class CheckoutRequest {
    private List<Item> items;
    private String guestEmail;
    private String paymentTypeId;
    private String cardToken; 

    public List<Item> getItems() {
        return items;
    }
    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getGuestEmail() {
        return guestEmail;
    }

    public void setGuestEmail(String guestEmail) {
        this.guestEmail = guestEmail;
    }

    public void setPaymentTypeId(String paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }
    public String getPaymentTypeId() {
        return paymentTypeId;
    }

    public String getCardToken() {
        return cardToken;
    }
    public void setCardToken(String cardToken) {
        this.cardToken = cardToken;
    }

    public static class Item {
        private Long productId;
        private int quantity;

        public Long getProductId() {
            return productId;
        }
        public void setProductId(Long productId) {
            this.productId = productId;
        }

        public int getQuantity() {
            return quantity;
        }
        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }

}
