package edu.tamu.jcabelloc.salepoint.data.local.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Purchase {

    public static final String STATUS_CREATED = "CREATED";

    @PrimaryKey(autoGenerate = true)
    private int purchaseId;
    private String status;
    private String user;

    public Purchase(int purchaseId, String status, String user) {
        this.purchaseId = purchaseId;
        this.status = status;
        this.user = user;
    }

    @Ignore
    public Purchase(String status, String user) {
        this.status = status;
        this.user = user;
    }

    public int getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(int purchaseId) {
        this.purchaseId = purchaseId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "purchaseId=" + purchaseId +
                ", status='" + status + '\'' +
                ", user='" + user + '\'' +
                '}';
    }
}
