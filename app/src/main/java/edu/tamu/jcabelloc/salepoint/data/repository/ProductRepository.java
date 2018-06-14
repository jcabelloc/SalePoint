package edu.tamu.jcabelloc.salepoint.data.repository;

import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import java.util.List;

import edu.tamu.jcabelloc.salepoint.data.dto.ListViewProduct;
import edu.tamu.jcabelloc.salepoint.data.local.dao.ProductDao;
import edu.tamu.jcabelloc.salepoint.data.local.entity.Product;

public class ProductRepository {

    private ProductDao productDao;
    private LiveData<List<ListViewProduct>> products;

    private static ProductRepository instance;

    private ProductRepository(ProductDao productDao){
        this.productDao = productDao;
        products = this.productDao.getAllProducts();
    }

    public static ProductRepository getInstance(ProductDao productDao){
        if (instance == null) {
            instance = new ProductRepository(productDao);
        }
        return instance;
    }

    public LiveData<List<ListViewProduct>> getAllProducts(){
        return products;
    }
    public void insert(Product product) {
        new InsertAsyncTask().execute(product);
    }

    private class InsertAsyncTask extends AsyncTask<Product, Void, Void> {

        @Override
        protected Void doInBackground(Product... products) {
            productDao.insert(products[0]);
            return null;
        }
    }
}
