package com.rajendra.onlinedailygroceries.model;

public class CartItem {
    private String productName;
    private String productDesc;
    private String productPrice;
    private String productQty;
    private String productUnit;

    private int imageResourceId;

    public CartItem(String productName, String productDesc, String productPrice, String productQty, String productUnit, int imageResourceId) {
        this.productName = productName;
        this.productDesc = productDesc;
        this.productPrice = productPrice;
        this.productQty = productQty;
        this.productUnit = productUnit;
        this.imageResourceId = imageResourceId;
    }

    public String getProductName() {
        return productName;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductQty() {
        return productQty;
    }

    public void setProductQty(String productQty) {
        this.productQty = productQty;
    }

    public String getProductUnit() {
        return productUnit;
    }

    public void setProductUnit(String productUnit) {
        this.productUnit = productUnit;
    }
}
