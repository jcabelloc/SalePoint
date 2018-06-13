package edu.tamu.jcabelloc.salepoint.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import edu.tamu.jcabelloc.salepoint.R;
import edu.tamu.jcabelloc.salepoint.data.dto.ListViewProduct;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductListViewHolder> {

    List<ListViewProduct> products;

    public class ProductListViewHolder extends RecyclerView.ViewHolder {
        ImageView productIconImageView;
        TextView productNameTextView;
        public ProductListViewHolder(View view){
            super(view);
            productIconImageView = view.findViewById(R.id.productIconImageView);
            productNameTextView = view.findViewById(R.id.productNameTextView);
            Log.d("JCC", "ProductListViewHolder");
        }
    }

    public ProductListAdapter(List<ListViewProduct> products){
        this.products = products;
    }

    //The adapter creates view holders as needed
    @NonNull
    @Override
    public ProductListAdapter.ProductListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View productItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_list, parent, false);
        ProductListViewHolder productListViewHolder = new ProductListViewHolder(productItem);
        Log.d("JCC", "onCreateViewHolder");
        return productListViewHolder;
    }
    //  binds the view holders to their data.
    // uses the view holder's position to determine what the contents should be, based on its list position.
    @Override
    public void onBindViewHolder(@NonNull ProductListViewHolder holder, int position) {
        holder.productIconImageView.setImageResource(R.drawable.ic_product);
        holder.productNameTextView.setText(products.get(position).getName());
        Log.d("JCC", "onBindViewHolder");
    }

    @Override
    public int getItemCount() {
        Log.d("JCC", "getItemCount:  " + String.valueOf(products.size()));
        return products.size();
    }


}
