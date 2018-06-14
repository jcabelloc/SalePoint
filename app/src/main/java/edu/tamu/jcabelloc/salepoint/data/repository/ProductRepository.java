package edu.tamu.jcabelloc.salepoint.data.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import edu.tamu.jcabelloc.salepoint.data.dto.ListViewProduct;
import edu.tamu.jcabelloc.salepoint.data.local.dao.ProductDao;
import edu.tamu.jcabelloc.salepoint.data.local.database.SaleAppDatabase;
import edu.tamu.jcabelloc.salepoint.data.local.entity.Product;

public class ProductRepository {

    private ProductDao productDao;


    public ProductRepository(Context context){

        SaleAppDatabase saleAppDatabase = SaleAppDatabase.getInstance(context);
        this.productDao = saleAppDatabase.productDao();
        Log.d("JCC", "saleAppDatabase: " + saleAppDatabase.hashCode());
    }

    public LiveData<List<ListViewProduct>> getAllProducts(){
        Log.d("JCC", "Thread - Product Repository - getAllProducts: " + Thread.currentThread());

        return productDao.getAllProducts();
    }
    public void insert(Product product) {
        Log.d("JCC", "Thread - Product Repository - insert: " + Thread.currentThread());
        //productDao.insert(product);
        new InsertAsyncTask().execute(product);
    }

    private class InsertAsyncTask extends AsyncTask<Product, Void, Void> {

        @Override
        protected Void doInBackground(Product... products) {
            Log.d("JCC", "Thread - Product Repository - InsertAsyncTask: " + Thread.currentThread());
            //productDao.deleteAll();
            productDao.insert(products[0]);
            return null;
        }
    }
}
