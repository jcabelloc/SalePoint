package edu.tamu.jcabelloc.salepoint.ui;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import edu.tamu.jcabelloc.salepoint.R;
import edu.tamu.jcabelloc.salepoint.ViewModel.ProductDetailViewModel;
import edu.tamu.jcabelloc.salepoint.ViewModel.ProductDetailViewModelFactory;
import edu.tamu.jcabelloc.salepoint.data.local.entity.Product;
import edu.tamu.jcabelloc.salepoint.utilities.InjectorUtils;

public class ProductDetailActivity extends AppCompatActivity {

    TextView productNameTextView;
    TextView categoryTextView;
    ImageView productImageView;
    TextView descriptionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        productNameTextView = findViewById(R.id.productNameTextView);
        categoryTextView = findViewById(R.id.categoryTextView);
        productImageView = findViewById(R.id.productImageView);
        descriptionTextView = findViewById(R.id.descriptionTextView);

        int id = getIntent().getIntExtra("id", -1);


        ProductDetailViewModelFactory productDetailViewModelFactory = InjectorUtils.getProductDetailViewModelFactory(getApplicationContext(), id);
        ProductDetailViewModel productDetailViewModel = ViewModelProviders.of(this, productDetailViewModelFactory).get(ProductDetailViewModel.class);
        productDetailViewModel.getProduct(id).observe(this, product -> {
            bindProductToUI(product);
        });


    }

    private void bindProductToUI(Product product) {
        productNameTextView.setText(product.getName());
        categoryTextView.setText("Articulos en General");
        productImageView.setImageBitmap(BitmapFactory.decodeByteArray(product.getImage(), 0, product.getImage().length));
        descriptionTextView.setText(product.getDescription());
    }
}
