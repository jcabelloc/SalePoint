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

    @Query("SELECT id, name, description, price, image FROM Product WHERE id = :id")
    LiveData<Product> getProduct(int id);

    @Query("SELECT id, name, description, price, image FROM Product ORDER BY id ASC")
    List<ListViewProduct> getProducts();

    @Query("SELECT id, name, description, price, image FROM Product WHERE name LIKE :productName  ORDER BY id ASC")
    List<ListViewProduct> getProductsByName(String productName);
}
