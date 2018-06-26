package edu.tamu.jcabelloc.salepoint.data.local.dao;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import edu.tamu.jcabelloc.salepoint.data.local.entity.Purchase;

@Dao
public interface PurchaseDao {

    @Query("SELECT * FROM Purchase WHERE user = :user AND status = :status")
    LiveData<Purchase> getPurchaseByUserAndStatus(String user, String status);

    @Insert
    void insert(Purchase purchase);
}
