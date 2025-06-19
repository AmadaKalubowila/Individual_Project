package com.freshsip.billservice;

import java.time.LocalDate;
import java.time.LocalTime;

public class BillingDTO {
    private Long billId;
    private String date;
    private String time;
    private Double cash;
    private Double balance;
    private Long cartId;
    private Double fullTotal;

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Double getCash() {
        return cash;
    }

    public void setCash(Double cash) {
        this.cash = cash;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }


    public double getFullTotal() {
        return fullTotal;
    }

    public void setFullTotal(double fullTotal) {
        this.fullTotal = fullTotal;
    }
}
