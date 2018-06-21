package edu.tamu.jcabelloc.salepoint.ViewModel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import edu.tamu.jcabelloc.salepoint.data.repository.OrderDetailRepository;

public class OrderDetailsViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final int orderId;
    private final OrderDetailRepository repository;

    public OrderDetailsViewModelFactory(int orderId, OrderDetailRepository repository) {
        this.orderId = orderId;
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new OrderDetailsViewModel(orderId, repository);
    }
}
