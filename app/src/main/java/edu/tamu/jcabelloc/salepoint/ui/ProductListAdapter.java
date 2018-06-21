package edu.tamu.jcabelloc.salepoint.ui;

import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

import edu.tamu.jcabelloc.salepoint.R;
import edu.tamu.jcabelloc.salepoint.data.dto.ListViewProduct;
import edu.tamu.jcabelloc.salepoint.data.local.entity.Product;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductListViewHolder> {

    List<ListViewProduct> products;
    OnItemClickListener listener;

    public class ProductListViewHolder extends RecyclerView.ViewHolder {
        ImageView productIconImageView;
        TextView productNameTextView;
        TextView descriptionTextView;
        TextView priceTextView;
        ImageButton addCartImageButton;
        public ProductListViewHolder(View view){
            super(view);
            productIconImageView = view.findViewById(R.id.productIconImageView);
            productNameTextView = view.findViewById(R.id.productNameTextView);
            descriptionTextView = view.findViewById(R.id.descriptionTextView);
            priceTextView = view.findViewById(R.id.priceTextView);
            addCartImageButton = view.findViewById(R.id.addCartImageButton);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(products.get(getAdapterPosition()).getId());
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int id);
    }

    public ProductListAdapter(List<ListViewProduct> products, OnItemClickListener listener){
        this.products = products;
        this.listener = listener;
    }

    //The adapter creates view holders as needed
    @NonNull
    @Override
    public ProductListAdapter.ProductListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View productItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_list, parent, false);
        ProductListViewHolder productListViewHolder = new ProductListViewHolder(productItem);
        return productListViewHolder;
    }

    //  binds the view holders to their data.
    // uses the view holder's position to determine what the contents should be, based on its list position.
    @Override
    public void onBindViewHolder(@NonNull ProductListViewHolder holder, int position) {
        holder.productIconImageView.setImageBitmap(BitmapFactory.decodeByteArray(products.get(position).getImage(), 0 , products.get(position).getImage().length));
        holder.productNameTextView.setText(products.get(position).getName().toUpperCase());
        // TODO refactor this section
        String description = products.get(position).getDescription();
        if (description == null || description.length() == 0) {
            description = "...";
        } else {
            description = description.length() > 30 ? description.substring(0, 28) + "..." : description;
        }
        holder.descriptionTextView.setText(description);
        holder.priceTextView.setText("S/" + String.valueOf(products.get(position).getPrice()));
        holder.addCartImageButton.setTag(products.get(position).getId());

    }

    @Override
    public int getItemCount() {
        return products.size();
    }


}
