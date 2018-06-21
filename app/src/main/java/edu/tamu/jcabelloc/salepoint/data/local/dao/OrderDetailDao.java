package edu.tamu.jcabelloc.salepoint.data.local.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import edu.tamu.jcabelloc.salepoint.data.local.entity.OrderDetail;

@Dao
public interface OrderDetailDao {
    @Query("SELECT * from OrderDetail WHERE orderId = :orderId")
    LiveData<List<OrderDetail>> getOrderDetailsByOrderId(int orderId);

    @Insert
    void insert(OrderDetail orderDetail);
}
