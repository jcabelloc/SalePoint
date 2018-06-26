package edu.tamu.jcabelloc.salepoint.ViewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import edu.tamu.jcabelloc.salepoint.data.local.entity.Purchase;
import edu.tamu.jcabelloc.salepoint.data.repository.PurchaseRepository;

public class PurchaseInProgressViewModel extends ViewModel {

    LiveData<Purchase> purchaseInProgress;
    PurchaseRepository purchaseRepository;

    PurchaseInProgressViewModel(String user, PurchaseRepository purchaseRepository) {
        this.purchaseInProgress = purchaseRepository.getPurchaseInProgress(user);
        this.purchaseRepository = purchaseRepository;
    }

    public LiveData<Purchase> getPurchaseInProgress() {
        return purchaseInProgress;
    }

    public void insert(Purchase purchase) {
        purchaseRepository.insert(purchase);
    }
}
