package edu.tamu.jcabelloc.salepoint.data.local.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import edu.tamu.jcabelloc.salepoint.data.local.entity.Order;

@Dao
public interface OrderDao {

    @Query("SELECT * FROM `Order` WHERE id = :orderId")
    LiveData<Order> getOrder(int orderId);

    @Query("SELECT * FROM `Order` WHERE user = :user AND status= :status")
    LiveData<Order> getOrderByUserAndStatus(String user, String status);

    @Insert
    void insert(Order order);
}
