package edu.tamu.jcabelloc.salepoint.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import edu.tamu.jcabelloc.salepoint.R;
import edu.tamu.jcabelloc.salepoint.ViewModel.ProductListViewModel;
import edu.tamu.jcabelloc.salepoint.ViewModel.ProductListViewModelFactory;
import edu.tamu.jcabelloc.salepoint.data.dto.ListViewProduct;
import edu.tamu.jcabelloc.salepoint.utilities.InjectorUtils;

public class ProductListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        // Set Toolbar
        Toolbar appToolbar = findViewById(R.id.appToolbar);
        setSupportActionBar(appToolbar);

        // Set the onClick event on the Floating Action Button
        FloatingActionButton addFloatingActionButton = findViewById(R.id.addFloatingActionButton);
        addFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NewProductActivity.class);
                startActivity(intent);
            }
        });

        // Set the recycler view configuration
        RecyclerView productListRecylerView = findViewById(R.id.productListRecylerView);
        // Same size no mather content -> better performance
        productListRecylerView.setHasFixedSize(true);

        // Set Layout Manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        productListRecylerView.setLayoutManager(linearLayoutManager);


        List<ListViewProduct> products = new ArrayList<>();
        ProductListAdapter productListAdapter = new ProductListAdapter(products);
        productListRecylerView.setAdapter(productListAdapter);


        ProductListViewModelFactory productListViewModelFactory = InjectorUtils.getProductListViewModelFactory(this);
        ProductListViewModel productListViewModel = ViewModelProviders.of(this, productListViewModelFactory).get(ProductListViewModel.class);
        Log.d("JCC", "Thread - ProductListActivity: " + Thread.currentThread());
        productListViewModel.getProducts().observe(this, productsList -> {
            Log.d("JCC", "Observed productsList: " + productsList.size() + " - " + Arrays.toString(productsList.toArray()));
            products.clear();
            products.addAll(productsList);
            productListAdapter.notifyDataSetChanged();

        });

        /*
        Handler handler = new Handler();
        handler.postDelayed( ()-> {
            List<Product> newProducts = new ArrayList<>();
            Random rand = new Random();
            newProducts.add(new Product(rand.nextInt(), "Product 1", 100.0));
            newProducts.add(new Product(rand.nextInt(), "Product 2", 200.0));
            newProducts.add(new Product(rand.nextInt(), "Product 3", 300.0));
            newProducts.add(new Product(rand.nextInt(), "Product 4", 400.0));
            newProducts.add(new Product(rand.nextInt(), "Product 5", 500.0));
            newProducts.add(new Product(rand.nextInt(), "Product 6", 500.0));
            newProducts.add(new Product(rand.nextInt(), "Product 7", 500.0));
            newProducts.add(new Product(rand.nextInt(), "Product 8", 100.0));
            newProducts.add(new Product(rand.nextInt(), "Product 9", 200.0));
            newProducts.add(new Product(rand.nextInt(), "Product 10", 300.0));
            newProducts.add(new Product(rand.nextInt(), "Product 11", 400.0));
            newProducts.add(new Product(rand.nextInt(), "Product 12", 500.0));
            newProducts.add(new Product(rand.nextInt(), "Product 13", 500.0));
            newProducts.add(new Product(rand.nextInt(), "Product 14", 500.0));
            newProducts.add(new Product(rand.nextInt(), "Product 15", 100.0));
            newProducts.add(new Product(rand.nextInt(), "Product 16", 200.0));
            newProducts.add(new Product(rand.nextInt(), "Product 17", 300.0));
            newProducts.add(new Product(rand.nextInt(), "Product 18", 400.0));
            newProducts.add(new Product(rand.nextInt(), "Product 19", 500.0));
            newProducts.add(new Product(rand.nextInt(), "Product 20", 500.0));
            newProducts.add(new Product(rand.nextInt(), "Product 21", 500.0));
            newProducts.add(new Product(rand.nextInt(), "Product 22", 400.0));
            newProducts.add(new Product(rand.nextInt(), "Product 23", 500.0));
            newProducts.add(new Product(rand.nextInt(), "Product 24", 500.0));
            newProducts.add(new Product(rand.nextInt(), "Product 25", 100.0));
            newProducts.add(new Product(rand.nextInt(), "Product 26", 200.0));
            newProducts.add(new Product(rand.nextInt(), "Product 27", 300.0));
            newProducts.add(new Product(rand.nextInt(), "Product 28", 400.0));
            newProducts.add(new Product(rand.nextInt(), "Product 29", 500.0));
            newProducts.add(new Product(rand.nextInt(), "Product 30", 500.0));
            newProducts.add(new Product(rand.nextInt(), "Product 31", 500.0));
            Log.d("JCC", "newProducts: " + Arrays.toString(newProducts.toArray()));
            productListViewModel.insert(newProducts.get(rand.nextInt(31)));
        }, 7000);
        */


    }
}
