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

public class ProductListViewModel extends ViewModel{

    LiveData<List<ListViewProduct>> products;

    ProductRepository productRepository;


    public ProductListViewModel(ProductRepository productRepository){

        this.productRepository = productRepository;
        products = productRepository.getAllProducts();

    }

    public LiveData<List<ListViewProduct>> getProducts(){
        return products;
    }

    public void insert(Product product) {
        productRepository.insert(product);
    }


}
