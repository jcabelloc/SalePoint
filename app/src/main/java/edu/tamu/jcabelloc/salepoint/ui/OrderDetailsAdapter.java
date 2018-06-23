package edu.tamu.jcabelloc.salepoint.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.tamu.jcabelloc.salepoint.R;

public class OrderDetailsAdapter extends RecyclerView.Adapter<OrderDetailsAdapter.OrderDetailsViewHolder> {

    public class OrderDetailsViewHolder extends RecyclerView.ViewHolder{

        public OrderDetailsViewHolder(View itemView) {
            super(itemView);
        }
    }


    @NonNull
    @Override
    public OrderDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View orderDetailView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_detail, parent, false );
        return new OrderDetailsViewHolder(orderDetailView);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderDetailsViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 20;
    }


}
