package edu.tamu.jcabelloc.salepoint.ViewModel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import edu.tamu.jcabelloc.salepoint.data.repository.OrderRepository;

public class OrderInProgressViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private OrderRepository orderRepository;
    private String user;
    public OrderInProgressViewModelFactory(String user, OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
        this.user = user;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new OrderInProgressViewModel(user, orderRepository);
    }
}
