package edu.tamu.jcabelloc.salepoint.ui;

import android.arch.lifecycle.ViewModelProviders;
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
import java.util.List;
import java.util.Random;

import edu.tamu.jcabelloc.salepoint.R;
import edu.tamu.jcabelloc.salepoint.ViewModel.ProductListViewModel;
import edu.tamu.jcabelloc.salepoint.data.dto.ListViewProduct;

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
                Toast.makeText(getApplicationContext(), "FAB tapped", Toast.LENGTH_LONG).show();
            }
        });

        // Set the recycler view configuration
        RecyclerView productListRecylerView = findViewById(R.id.productListRecylerView);
        // Same size no mather content -> better performance
        productListRecylerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        productListRecylerView.setLayoutManager(linearLayoutManager);


        ProductListViewModel productListViewModel = ViewModelProviders.of(this).get(ProductListViewModel.class);

        List<ListViewProduct> products = new ArrayList<>();


        ProductListAdapter productListAdapter = new ProductListAdapter(products);
        productListRecylerView.setAdapter(productListAdapter);

        //products.addAll(productListViewModel.getProducts());

        productListViewModel.getProducts().observe(this, productsList -> {
            products.addAll(productsList);
            productListAdapter.notifyDataSetChanged();

        });

        Handler handler = new Handler();
        handler.postDelayed( ()-> {
            List<ListViewProduct> newProducts = new ArrayList<>();
            Random rand = new Random();
            newProducts.add(new ListViewProduct(rand.nextInt(), "Product 11", 400.0));
            newProducts.add(new ListViewProduct(rand.nextInt(), "Product 12", 500.0));
            newProducts.add(new ListViewProduct(rand.nextInt(), "Product 13", 500.0));
            newProducts.add(new ListViewProduct(rand.nextInt(), "Product 14", 500.0));
            newProducts.add(new ListViewProduct(rand.nextInt(), "Product 15", 100.0));
            newProducts.add(new ListViewProduct(rand.nextInt(), "Product 16", 200.0));
            newProducts.add(new ListViewProduct(rand.nextInt(), "Product 17", 300.0));
            newProducts.add(new ListViewProduct(rand.nextInt(), "Product 18", 400.0));
            newProducts.add(new ListViewProduct(rand.nextInt(), "Product 19", 500.0));
            newProducts.add(new ListViewProduct(rand.nextInt(), "Product 20", 500.0));
            newProducts.add(new ListViewProduct(rand.nextInt(), "Product 21", 500.0));
            productListViewModel.addProducts(newProducts);
        }, 5000);



    }
}
