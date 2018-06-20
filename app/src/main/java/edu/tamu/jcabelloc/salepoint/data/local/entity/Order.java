package edu.tamu.jcabelloc.salepoint.data.local.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Order {
    public static final String STATUS_CREATED = "CREATED";
    public static final String STATUS_SOLD = "SOLD";

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String status;
    private int numOfItems;
    private String user;

    public Order(int id, String status, int numOfItems, String user) {
        this.id = id;
        this.status = status;
        this.numOfItems = numOfItems;
        this.user = user;
    }
    @Ignore
    public Order(String status, int numOfItems, String user) {
        this.status = status;
        this.numOfItems = numOfItems;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getNumOfItems() {
        return numOfItems;
    }

    public void setNumOfItems(int numOfItems) {
        this.numOfItems = numOfItems;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", numOfItems=" + numOfItems +
                ", user='" + user + '\'' +
                '}';
    }
}
