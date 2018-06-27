package edu.tamu.jcabelloc.salepoint.ui;

import android.app.SearchManager;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

import edu.tamu.jcabelloc.salepoint.R;
import edu.tamu.jcabelloc.salepoint.ViewModel.PurchaseDetailsViewModel;
import edu.tamu.jcabelloc.salepoint.ViewModel.PurchaseDetailsViewModelFactory;
import edu.tamu.jcabelloc.salepoint.ViewModel.PurchaseInProgressViewModelFactory;
import edu.tamu.jcabelloc.salepoint.ViewModel.PurchaseInProgressViewModel;
import edu.tamu.jcabelloc.salepoint.data.dto.ListViewPurchaseDetail;
import edu.tamu.jcabelloc.salepoint.data.local.entity.Purchase;
import edu.tamu.jcabelloc.salepoint.data.local.entity.PurchaseDetail;
import edu.tamu.jcabelloc.salepoint.utilities.InjectorUtils;

public class PurchaseDetailsActivity extends AppCompatActivity {

    String user = "system";
    List<ListViewPurchaseDetail> purchaseDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_details);

        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) findViewById(R.id.productSearchView);
        // Assumes current activity is the searchable activity
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        // Do not iconify the widget; expand it by default
        searchView.setIconifiedByDefault(false);


        RecyclerView purchaseDetailsRecylerView = findViewById(R.id.purchaseDetailsRecylerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        purchaseDetailsRecylerView.setLayoutManager(linearLayoutManager);
        purchaseDetailsRecylerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));


        purchaseDetails = new ArrayList<>();

        PurchaseDetailsAdapter purchaseDetailsAdapter = new PurchaseDetailsAdapter(purchaseDetails);
        purchaseDetailsRecylerView.setAdapter(purchaseDetailsAdapter);

        PurchaseInProgressViewModelFactory purchaseInProgressViewModelFactory = InjectorUtils.getPurchaseInProgressViewModelFactory(getApplicationContext(), user);
        PurchaseInProgressViewModel purchaseInProgressViewModel = ViewModelProviders.of(this, purchaseInProgressViewModelFactory).get(PurchaseInProgressViewModel.class);
        purchaseInProgressViewModel.getPurchaseInProgress().observe(this, purchase -> {
            if (purchase != null) {

                PurchaseDetailsViewModelFactory purchaseDetailsVMFactory = InjectorUtils.getPurchaseDetailsViewModelFactory(getApplicationContext(), purchase.getPurchaseId());
                PurchaseDetailsViewModel purchaseDetailsVM = ViewModelProviders.of(this, purchaseDetailsVMFactory).get(PurchaseDetailsViewModel.class);
                purchaseDetailsVM.getPurchaseDetails().observe(this, purchaseDetailsObserved -> {
                    // TODO Refactor this part
                    //purchaseDetails = purchaseDetailsObserved;
                    purchaseDetails.clear();
                    purchaseDetails.addAll(purchaseDetailsObserved);
                    purchaseDetailsAdapter.notifyDataSetChanged();
                });
                //PurchaseDetail tempPurchaseDetail = new PurchaseDetail(purchase.getPurchaseId(), 4, 12, 1.20, 14.40);
                //purchaseDetailsVM.insert(tempPurchaseDetail);

            } else {
                Purchase purchaseInProgres = new Purchase(Purchase.STATUS_CREATED, user);
                purchaseInProgressViewModel.insert(purchaseInProgres);
            }
        });


    }
}
