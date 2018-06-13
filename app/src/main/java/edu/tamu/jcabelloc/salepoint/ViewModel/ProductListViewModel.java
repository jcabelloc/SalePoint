package edu.tamu.jcabelloc.salepoint.ViewModel;

import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.tamu.jcabelloc.salepoint.data.dto.ListViewProduct;

public class ProductListViewModel extends ViewModel{

    List<ListViewProduct> products;

    public ProductListViewModel(){
        products = new ArrayList<>();
        Random rand = new Random();

        products.add(new ListViewProduct(rand.nextInt(), "Product 1", 100.0));
        products.add(new ListViewProduct(rand.nextInt(), "Product 2", 200.0));
        products.add(new ListViewProduct(rand.nextInt(), "Product 3", 300.0));
        products.add(new ListViewProduct(rand.nextInt(), "Product 4", 400.0));
        products.add(new ListViewProduct(rand.nextInt(), "Product 5", 500.0));
        products.add(new ListViewProduct(rand.nextInt(), "Product 6", 500.0));
        products.add(new ListViewProduct(rand.nextInt(), "Product 7", 500.0));
        products.add(new ListViewProduct(rand.nextInt(), "Product 8", 100.0));
        products.add(new ListViewProduct(rand.nextInt(), "Product 9", 200.0));
        products.add(new ListViewProduct(rand.nextInt(), "Product 10", 300.0));
        products.add(new ListViewProduct(rand.nextInt(), "Product 11", 400.0));
        products.add(new ListViewProduct(rand.nextInt(), "Product 12", 500.0));
        products.add(new ListViewProduct(rand.nextInt(), "Product 13", 500.0));
        products.add(new ListViewProduct(rand.nextInt(), "Product 14", 500.0));
        products.add(new ListViewProduct(rand.nextInt(), "Product 15", 100.0));
        products.add(new ListViewProduct(rand.nextInt(), "Product 16", 200.0));
        products.add(new ListViewProduct(rand.nextInt(), "Product 17", 300.0));
        products.add(new ListViewProduct(rand.nextInt(), "Product 18", 400.0));
        products.add(new ListViewProduct(rand.nextInt(), "Product 19", 500.0));
        products.add(new ListViewProduct(rand.nextInt(), "Product 20", 500.0));
        products.add(new ListViewProduct(rand.nextInt(), "Product 21", 500.0));

    }

    public List<ListViewProduct> getProducts(){
        return products;
    }
}
