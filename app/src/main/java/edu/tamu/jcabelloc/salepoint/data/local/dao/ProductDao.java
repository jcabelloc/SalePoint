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

    @Query("SELECT id, name, description, price, image FROM Product ORDER BY id ASC")
    LiveData<List<ListViewProduct>> getAllProducts();

    @Query("DELETE FROM Product")
    void deleteAll();

    @Insert
    void insert(Product product);

}
