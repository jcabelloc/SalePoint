package edu.tamu.jcabelloc.salepoint.data.local.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Order {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String status;
    private int numOfItems;

    public Order(int id, String status, int numOfItems) {
        this.id = id;
        this.status = status;
        this.numOfItems = numOfItems;
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

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", numOfItems=" + numOfItems +
                '}';
    }
}
