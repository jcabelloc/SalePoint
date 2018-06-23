package edu.tamu.jcabelloc.salepoint.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import edu.tamu.jcabelloc.salepoint.R;
import edu.tamu.jcabelloc.salepoint.ViewModel.OrderDetailsViewModel;
import edu.tamu.jcabelloc.salepoint.ViewModel.OrderDetailsViewModelFactory;
import edu.tamu.jcabelloc.salepoint.ViewModel.ProductListViewModel;
import edu.tamu.jcabelloc.salepoint.ViewModel.ProductListViewModelFactory;
import edu.tamu.jcabelloc.salepoint.data.dto.ListViewOrderDetail;
import edu.tamu.jcabelloc.salepoint.data.dto.ListViewProduct;
import edu.tamu.jcabelloc.salepoint.data.local.entity.OrderDetail;
import edu.tamu.jcabelloc.salepoint.data.local.entity.Product;
import edu.tamu.jcabelloc.salepoint.utilities.InjectorUtils;

public class OrderDetailsActivity extends AppCompatActivity implements OrderDetailsAdapter.OnClickListener {

    OrderDetailsViewModel orderDetailsViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        RecyclerView recyclerView = findViewById(R.id.orderDetailsRecylerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        List<ListViewOrderDetail> listViewOrderDetails = new ArrayList<>();
        OrderDetailsAdapter orderDetailsAdapter = new OrderDetailsAdapter(listViewOrderDetails, this);
        recyclerView.setAdapter(orderDetailsAdapter);

        int orderId = getIntent().getIntExtra("orderId", 0);

        OrderDetailsViewModelFactory orderDetailsViewModelFactory = InjectorUtils.getOrderDetailViewModelFactory(getApplicationContext(), orderId);
        orderDetailsViewModel = ViewModelProviders.of(this, orderDetailsViewModelFactory).get(OrderDetailsViewModel.class);
        orderDetailsViewModel.getOrderDetails().observe(this, orderDetailsObserved -> {

            // TODO Refactor this part. Include just products in Order
            ProductListViewModelFactory productListViewModelFactory = InjectorUtils.getProductListViewModelFactory(getApplicationContext());
            ProductListViewModel productListViewModel = ViewModelProviders.of(this, productListViewModelFactory).get(ProductListViewModel.class);
            productListViewModel.getProducts().observe(this, products -> {
                listViewOrderDetails.clear();
                for(OrderDetail orderDetail:orderDetailsObserved) {
                    ListViewProduct product = findProductById(products, orderDetail.getProductId());
                    listViewOrderDetails.add(new ListViewOrderDetail(orderDetail.getId(), orderDetail.getOrderId(),
                            product.getName(), product.getDescription(), orderDetail.getQuantity(), orderDetail.getUnitPrice(),
                            orderDetail.getSubTotal(), product.getImage()));
                }
                orderDetailsAdapter.notifyDataSetChanged();
            });
        });
    }
    private ListViewProduct findProductById(List<ListViewProduct> products, int productId) {
        for(ListViewProduct product: products) {
            if (product.getId() == productId) {
                return product;
            }
        }
        return null;
    }

    @Override
    public void onAddQuantityClick(int orderDetailId) {
        orderDetailsViewModel.addQuantityByOne(orderDetailId);
    }

    @Override
    public void onSubtractQuantityClick(int orderDetailId) {
        orderDetailsViewModel.subtractQuantityByOne(orderDetailId);
    }

    @Override
    public void onDeleteClick(int orderDetailId) {
        orderDetailsViewModel.deleteOrderDetail(orderDetailId);
    }
}
