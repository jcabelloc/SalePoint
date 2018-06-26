package edu.tamu.jcabelloc.salepoint.ui;

import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import edu.tamu.jcabelloc.salepoint.R;
import edu.tamu.jcabelloc.salepoint.data.dto.ListViewPurchaseDetail;

public class PurchaseDetailsAdapter extends RecyclerView.Adapter<PurchaseDetailsAdapter.PurchaseDetailViewHolder> {

    List<ListViewPurchaseDetail> purchaseDetails;
    public PurchaseDetailsAdapter(List<ListViewPurchaseDetail> purchaseDetails) {
        this.purchaseDetails = purchaseDetails;
    }

    class PurchaseDetailViewHolder extends RecyclerView.ViewHolder{
        TextView productNameTextView;
        TextView descriptionTextView;
        ImageView productIconImageView;
        TextInputEditText montoTextInputEditText;
        TextInputEditText quantityTextInputEditText;
        TextView unitCostTextView;


        public PurchaseDetailViewHolder(View itemView) {
            super(itemView);
            productNameTextView = itemView.findViewById(R.id.productNameTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
            productIconImageView = itemView.findViewById(R.id.productIconImageView);
            montoTextInputEditText = itemView.findViewById(R.id.montoTextInputEditText);
            quantityTextInputEditText = itemView.findViewById(R.id.quantityTextInputEditText);
            unitCostTextView = itemView.findViewById(R.id.unitCostTextView);
        }
    }


    @NonNull
    @Override
    public PurchaseDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_purchase_detail, parent, false);
        return new PurchaseDetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PurchaseDetailViewHolder holder, int position) {
        holder.productNameTextView.setText(purchaseDetails.get(position).getProductName());
        holder.descriptionTextView.setText(purchaseDetails.get(position).getProductDescription());
        holder.productIconImageView.setImageBitmap(BitmapFactory.decodeByteArray(purchaseDetails.get(position).getImage(), 0, purchaseDetails.get(position).getImage().length));


    }

    @Override
    public int getItemCount() {
        return purchaseDetails.size();
    }


}
