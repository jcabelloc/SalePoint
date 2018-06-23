package edu.tamu.jcabelloc.salepoint.ui;

import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import edu.tamu.jcabelloc.salepoint.R;
import edu.tamu.jcabelloc.salepoint.data.dto.ListViewOrderDetail;

public class OrderDetailsAdapter extends RecyclerView.Adapter<OrderDetailsAdapter.OrderDetailsViewHolder> {

    private List<ListViewOrderDetail> orderDetails;
    private OnClickListener listener;

    public OrderDetailsAdapter(List<ListViewOrderDetail> orderDetails, OnClickListener listener) {
        this.orderDetails = orderDetails;
        this.listener = listener;
    }

    public class OrderDetailsViewHolder extends RecyclerView.ViewHolder{
        TextView productNameTextView;
        TextView descriptionTextView;
        ImageView productIconImageView;
        TextView quantityTextView;
        TextView subTotalTextView;
        public OrderDetailsViewHolder(View itemView) {
            super(itemView);
            this.productNameTextView = itemView.findViewById(R.id.productNameTextView);
            this.descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
            this.productIconImageView = itemView.findViewById(R.id.productIconImageView);
            this.quantityTextView = itemView.findViewById(R.id.quantityTextView);
            this.subTotalTextView = itemView.findViewById(R.id.subTotalTextView);
            ImageButton addImageButton = itemView.findViewById(R.id.addImageButton);
            ImageButton subtractImageButton = itemView.findViewById(R.id.subtractImageButton);
            ImageButton deleteImageButton = itemView.findViewById(R.id.deleteImageButton);
            addImageButton.setOnClickListener((view) -> {
                listener.onAddQuantityClick(orderDetails.get(getAdapterPosition()).getOrderDetailId());
            });
            subtractImageButton.setOnClickListener((view) -> {
                listener.onSubtractQuantityClick(orderDetails.get(getAdapterPosition()).getOrderDetailId());
            });
            deleteImageButton.setOnClickListener( (view) -> {
                listener.onDeleteClick(orderDetails.get(getAdapterPosition()).getOrderDetailId());
            });
        }
    }

    public interface OnClickListener {
        void onAddQuantityClick(int orderDetailId);
        void onSubtractQuantityClick(int orderDetailId);
        void onDeleteClick(int orderDetailId);
    }

    @NonNull
    @Override
    public OrderDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View orderDetailView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_detail, parent, false );
        return new OrderDetailsViewHolder(orderDetailView);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderDetailsViewHolder holder, int position) {
        holder.productNameTextView.setText(orderDetails.get(position).getProductName());
        holder.descriptionTextView.setText(orderDetails.get(position).getProductDescription());
        holder.productIconImageView.setImageBitmap(BitmapFactory.decodeByteArray(orderDetails.get(position).getImage(), 0, orderDetails.get(position).getImage().length));
        holder.quantityTextView.setText(orderDetails.get(position).getQuantity() + " und");
        holder.subTotalTextView.setText("S/" + orderDetails.get(position).getSubTotal());
    }

    @Override
    public int getItemCount() {
        return orderDetails.size();
    }


}
