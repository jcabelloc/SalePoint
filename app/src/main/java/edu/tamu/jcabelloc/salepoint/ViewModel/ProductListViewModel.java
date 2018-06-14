package edu.tamu.jcabelloc.salepoint.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


import edu.tamu.jcabelloc.salepoint.data.dto.ListViewProduct;
import edu.tamu.jcabelloc.salepoint.data.local.entity.Product;
import edu.tamu.jcabelloc.salepoint.data.repository.ProductRepository;

public class ProductListViewModel extends AndroidViewModel{

    LiveData<List<ListViewProduct>> products;

    ProductRepository productRepository;


    public ProductListViewModel(Application application){
        super(application);
        productRepository = new ProductRepository(application);
        Log.d("JCC", "productRepository: " + productRepository.hashCode());
        products = productRepository.getAllProducts();

        /*
        List<ListViewProduct> productsList = new ArrayList<>();
        Random rand = new Random();



        products = new MutableLiveData<>();
        products.setValue(productsList);
        */
    }

    public LiveData<List<ListViewProduct>> getProducts(){
        Log.d("JCC", "Thread - ProductListViewModel - getProducts: " + Thread.currentThread());
        return products;
    }

    public void insert(Product product) {
        Log.d("JCC", "Thread - ProductListViewModel - insert: " + Thread.currentThread());
        productRepository.insert(product);
    }

//    public void addProducts(List<Product> newProducts){
//        products.postValue(newProducts);
//
//    }
}
