package edu.tamu.jcabelloc.salepoint.data.dto;

public class ListViewOrderDetail {

    private int orderDetailId;
    private int orderId;
    private String productName;
    private String productDescription;
    private int quantity;
    private double unitPrice;
    private double subTotal;
    private byte[] image;

    public ListViewOrderDetail(int orderDetailId, int orderId, String productName, String productDescription, int quantity, double unitPrice, double subTotal, byte[] image) {
        this.orderDetailId = orderDetailId;
        this.orderId = orderId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.subTotal = subTotal;
        this.image = image;
    }

    public int getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(int orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "ListViewOrderDetail{" +
                "orderDetailId=" + orderDetailId +
                ", orderId=" + orderId +
                ", productName='" + productName + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                ", subTotal=" + subTotal +
                '}';
    }
}
