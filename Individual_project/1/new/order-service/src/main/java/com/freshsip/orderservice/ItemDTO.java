package com.freshsip.orderservice;


public class ItemDTO {
    private Long item_id;
    private String item_name;
    private byte[] image;
    private String description;
    private double item_prize;
    private int quantity;

    public Long getItem_id() {
        return item_id;
    }

    public void setItem_id(Long item_id) {
        this.item_id = item_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getItem_prize() {
        return item_prize;
    }

    public void setItem_prize(double item_prize) {
        this.item_prize = item_prize;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
