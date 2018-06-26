package edu.tamu.jcabelloc.salepoint.ViewModel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import edu.tamu.jcabelloc.salepoint.data.repository.PurchaseRepository;

public class PurchaseInProgressViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final String user;
    private final PurchaseRepository purchaseRepository;
    public PurchaseInProgressViewModelFactory(String user, PurchaseRepository purchaseRepository) {
        this.user = user;
        this.purchaseRepository = purchaseRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new PurchaseInProgressViewModel(user, purchaseRepository);
    }
}
