package edu.tamu.jcabelloc.salepoint.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import edu.tamu.jcabelloc.salepoint.R;
import edu.tamu.jcabelloc.salepoint.ViewModel.OrderDetailsViewModel;
import edu.tamu.jcabelloc.salepoint.ViewModel.OrderDetailsViewModelFactory;
import edu.tamu.jcabelloc.salepoint.ViewModel.OrderInProgressViewModel;
import edu.tamu.jcabelloc.salepoint.ViewModel.OrderInProgressViewModelFactory;
import edu.tamu.jcabelloc.salepoint.ViewModel.ProductListViewModel;
import edu.tamu.jcabelloc.salepoint.ViewModel.ProductListViewModelFactory;
import edu.tamu.jcabelloc.salepoint.data.dto.ListViewProduct;
import edu.tamu.jcabelloc.salepoint.data.local.entity.Order;
import edu.tamu.jcabelloc.salepoint.data.local.entity.OrderDetail;
import edu.tamu.jcabelloc.salepoint.drawable.CountDrawable;
import edu.tamu.jcabelloc.salepoint.utilities.InjectorUtils;

public class ProductListActivity extends AppCompatActivity {

    String user = "system";
    Order orderInProgress;
    OrderDetailsViewModel orderDetailsViewModel;
    List<OrderDetail> orderDetails = new ArrayList<>();
    Menu defaultMenu;


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
        productListRecylerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));



        List<ListViewProduct> products = new ArrayList<>();
        ProductListAdapter productListAdapter = new ProductListAdapter(products, new ProductListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int id) {
                Intent intent = new Intent(getApplicationContext(), ProductDetailActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
        productListRecylerView.setAdapter(productListAdapter);


        ProductListViewModelFactory productListViewModelFactory = InjectorUtils.getProductListViewModelFactory(getApplicationContext());
        ProductListViewModel productListViewModel = ViewModelProviders.of(this, productListViewModelFactory).get(ProductListViewModel.class);
        productListViewModel.getProducts().observe(this, productsList -> {
            products.clear();
            products.addAll(productsList);
            productListAdapter.notifyDataSetChanged();

        });

        // Accessing o Creating Order in Progress
        // Business Rule: There must be just one order in progress per user
        OrderInProgressViewModelFactory orderInProgressViewModelFactory = InjectorUtils.getOrderViewModelFactory(getApplicationContext(), user);
        OrderInProgressViewModel orderInProgressViewModel = ViewModelProviders.of(this, orderInProgressViewModelFactory).get(OrderInProgressViewModel.class);
        orderInProgressViewModel.getOrderInProgress().observe(this, order -> {
            if (order != null) {
                orderInProgress = order;
                OrderDetailsViewModelFactory orderDetailsViewModelFactory = InjectorUtils.getOrderDetailViewModelFactory(getApplicationContext(), orderInProgress.getId());
                orderDetailsViewModel = ViewModelProviders.of(this, orderDetailsViewModelFactory).get(OrderDetailsViewModel.class);
                orderDetailsViewModel.getOrderDetails().observe(this, orderDetailsObserved -> {
                    orderDetails = orderDetailsObserved;
                    invalidateOptionsMenu();
                });

            } else {
                orderInProgress = new Order(Order.STATUS_CREATED, 0, user);
                orderInProgressViewModel.insert(orderInProgress);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_product_list, menu);
        defaultMenu = menu;
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        setItemsCountOnCart(this, String.valueOf(orderDetails.size()));
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                Toast.makeText(this, "Settings Tapped", Toast.LENGTH_SHORT).show();
                Intent tempIntent = new Intent(getApplicationContext(), PurchaseDetailsActivity.class);
                startActivity(tempIntent);
                return true;

            case R.id.action_search:
                Toast.makeText(this, "Search Tapped", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.action_shopping_cart:
                Toast.makeText(this, "Shopping Cart Tapped", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), OrderDetailsActivity.class);
                intent.putExtra("orderId", orderInProgress.getId());
                startActivity(intent);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    public void addProductToCart(View view) {
        int productId = Integer.valueOf(view.getTag().toString());
        if (orderDetailsContainsProduct(orderDetails, productId)) {
            Toast.makeText(this, "Product is already on Cart", Toast.LENGTH_LONG).show();
        } else {
            OrderDetail newOrderDetail = new OrderDetail(orderInProgress.getId(), productId);
            orderDetailsViewModel.addOrderDetailToCart(newOrderDetail);
            Toast.makeText(this, "Product Added", Toast.LENGTH_LONG).show();
        }
    }

    public boolean orderDetailsContainsProduct(final List<OrderDetail> orderDetails, final int productId){
        //return orderDetails.stream().filter(o -> o.getProductId().equals(productId)).findFirst().isPresent();
        for (OrderDetail orderDetail : orderDetails) {
                if (orderDetail.getProductId() == productId ) {
                    return true;
                }
        }
        return false;
    }

    public void setItemsCountOnCart(Context context, String count) {
        MenuItem menuItem = defaultMenu.findItem(R.id.action_shopping_cart);
        LayerDrawable icon = (LayerDrawable) menuItem.getIcon();

        CountDrawable badge;

        // Reuse drawable if possible
        Drawable reuse = icon.findDrawableByLayerId(R.id.ic_group_count);
        if (reuse != null && reuse instanceof CountDrawable) {
            badge = (CountDrawable) reuse;
        } else {
            badge = new CountDrawable(context);
        }

        badge.setCount(count);
        icon.mutate();
        icon.setDrawableByLayerId(R.id.ic_group_count, badge);
    }
}
