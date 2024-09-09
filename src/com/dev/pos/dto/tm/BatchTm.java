package com.dev.pos.dto.tm;

import javafx.scene.control.Button;

public class BatchTm {
    private String code;
    private int qty;
    private double buyingPrice;
    private boolean discount;
    private double showPrice;
    private double sellingPrice;
    private Button button;

    public BatchTm() {
    }

    public BatchTm(String code, int qty, double buyingPrice, boolean discount, double showPrice, double sellingPrice, Button button) {
        this.code = code;
        this.qty = qty;
        this.buyingPrice = buyingPrice;
        this.discount = discount;
        this.showPrice = showPrice;
        this.sellingPrice = sellingPrice;
        this.button = button;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getBuyingPrice() {
        return buyingPrice;
    }

    public void setBuyingPrice(double buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    public boolean isDiscount() {
        return discount;
    }

    public void setDiscount(boolean discount) {
        this.discount = discount;
    }

    public double getShowPrice() {
        return showPrice;
    }

    public void setShowPrice(double showPrice) {
        this.showPrice = showPrice;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }
}
