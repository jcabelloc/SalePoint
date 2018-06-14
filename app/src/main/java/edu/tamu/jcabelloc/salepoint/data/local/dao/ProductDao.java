package edu.tamu.jcabelloc.salepoint.data.local.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import edu.tamu.jcabelloc.salepoint.data.dto.ListViewProduct;
import edu.tamu.jcabelloc.salepoint.data.local.entity.Product;

@Dao
public interface ProductDao {

    @Query("SELECT id, name, price FROM Product")
    LiveData<List<ListViewProduct>> getAllProducts();

    @Query("DELETE FROM Product")
    void deleteAll();

    @Insert
    void insert(Product product);

}
