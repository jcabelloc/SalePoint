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
        ProductListAdapter productListAdapter = new ProductListAdapter(products, new ProductListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int id) {
                Intent intent = new Intent(getApplicationContext(), ProductDetailActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Element Clicked: " + id, Toast.LENGTH_LONG).show();
            }
        });
        productListRecylerView.setAdapter(productListAdapter);


        ProductListViewModelFactory productListViewModelFactory = InjectorUtils.getProductListViewModelFactory(getApplicationContext());
        ProductListViewModel productListViewModel = ViewModelProviders.of(this, productListViewModelFactory).get(ProductListViewModel.class);
        Log.d("JCC", "Thread - ProductListActivity: " + Thread.currentThread());
        productListViewModel.getProducts().observe(this, productsList -> {
            Log.d("JCC", "Observed productsList: " + productsList.size() + " - " + Arrays.toString(productsList.toArray()));
            products.clear();
            products.addAll(productsList);
            productListAdapter.notifyDataSetChanged();

        });

    }
    public void onItemClick() {
        Toast.makeText(this, "Item Clicked", Toast.LENGTH_LONG).show();
    }
}
