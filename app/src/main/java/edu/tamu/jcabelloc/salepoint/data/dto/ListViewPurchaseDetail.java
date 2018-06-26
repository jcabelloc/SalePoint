package edu.tamu.jcabelloc.salepoint.data.dto;

import java.util.Arrays;

public class ListViewPurchaseDetail {
    private int purchaseId;
    private int purchaseDetailId;
    private int productId;
    private String productName;
    private String productDescription;
    private byte[] image;
    private int quantity;
    private double unitCost;
    private double subTotal;

    public ListViewPurchaseDetail(int purchaseId, int purchaseDetailId, int productId, String productName, String productDescription, byte[] image, int quantity, double unitCost, double subTotal) {
        this.purchaseId = purchaseId;
        this.purchaseDetailId = purchaseDetailId;
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.image = image;
        this.quantity = quantity;
        this.unitCost = unitCost;
        this.subTotal = subTotal;
    }

    public int getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(int purchaseId) {
        this.purchaseId = purchaseId;
    }

    public int getPurchaseDetailId() {
        return purchaseDetailId;
    }

    public void setPurchaseDetailId(int purchaseDetailId) {
        this.purchaseDetailId = purchaseDetailId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(double unitCost) {
        this.unitCost = unitCost;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    @Override
    public String toString() {
        return "ListViewPurchaseDetail{" +
                "purchaseId=" + purchaseId +
                ", purchaseDetailId=" + purchaseDetailId +
                ", productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", image=" + Arrays.toString(image) +
                ", quantity=" + quantity +
                ", unitCost=" + unitCost +
                ", subTotal=" + subTotal +
                '}';
    }
}

