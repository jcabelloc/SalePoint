package edu.tamu.jcabelloc.salepoint.ui;

import android.arch.lifecycle.ViewModelProviders;
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

        products.addAll(productListViewModel.getProducts());
        Log.d("JCC",  "Number of Products:  " + String.valueOf(products.size()));

        productListAdapter.notifyDataSetChanged();


    }
}
