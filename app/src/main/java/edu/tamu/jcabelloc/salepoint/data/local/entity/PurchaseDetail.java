package edu.tamu.jcabelloc.salepoint.data.local.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(indices = { @Index(value = {"purchaseId"}),
                    @Index(value = {"purchaseId", "productId"}, unique = true) },
        foreignKeys = {@ForeignKey( entity = Purchase.class,
                                    parentColumns = "purchaseId",
                                    childColumns = "purchaseId",
                                    onDelete = CASCADE),
                       @ForeignKey( entity = Product.class,
                                    parentColumns = "id",
                                    childColumns = "productId")
                        })
public class PurchaseDetail {

    @PrimaryKey(autoGenerate = true)
    private int purchaseDetailId;
    private int purchaseId;
    private int productId;
    private int quantity;
    private double unitCost;
    private double subTotal;

    public PurchaseDetail(int purchaseDetailId, int purchaseId, int productId, int quantity, double unitCost, double subTotal) {
        this.purchaseDetailId = purchaseDetailId;
        this.purchaseId = purchaseId;
        this.productId = productId;
        this.quantity = quantity;
        this.unitCost = unitCost;
        this.subTotal = subTotal;
    }

    @Ignore
    public PurchaseDetail(int purchaseId, int productId, int quantity, double unitCost, double subTotal) {
        this.purchaseId = purchaseId;
        this.productId = productId;
        this.quantity = quantity;
        this.unitCost = unitCost;
        this.subTotal = subTotal;
    }

    public int getPurchaseDetailId() {
        return purchaseDetailId;
    }

    public void setPurchaseDetailId(int purchaseDetailId) {
        this.purchaseDetailId = purchaseDetailId;
    }

    public int getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(int purchaseId) {
        this.purchaseId = purchaseId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
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
        return "PurchaseDetail{" +
                "purchaseDetailId=" + purchaseDetailId +
                ", purchaseId=" + purchaseId +
                ", productId=" + productId +
                ", quantity=" + quantity +
                ", unitCost=" + unitCost +
                ", subTotal=" + subTotal +
                '}';
    }
}
