package edu.tamu.jcabelloc.salepoint.ViewModel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import edu.tamu.jcabelloc.salepoint.data.repository.PurchaseDetailRepository;

public class PurchaseDetailsViewModelFactory extends ViewModelProvider.NewInstanceFactory{

    private final int purchaseId;
    private final PurchaseDetailRepository purchaseDetailRepository;

    public PurchaseDetailsViewModelFactory(int purchaseId, PurchaseDetailRepository purchaseDetailRepository) {
        this.purchaseId = purchaseId;
        this.purchaseDetailRepository = purchaseDetailRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T)new PurchaseDetailsViewModel(purchaseId, purchaseDetailRepository);
    }
}
