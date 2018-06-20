package edu.tamu.jcabelloc.salepoint.ViewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import edu.tamu.jcabelloc.salepoint.data.local.entity.Order;
import edu.tamu.jcabelloc.salepoint.data.repository.OrderRepository;

public class OrderInProgressViewModel extends ViewModel {

    private LiveData<Order> order;
    private OrderRepository orderRepository;

    public OrderInProgressViewModel(String user, OrderRepository orderRepository){
        this.order = orderRepository.getOrderInProgress(user);
        this.orderRepository = orderRepository;
    }

    public LiveData<Order> getOrderInProgress() {
        return order;
    }

    public void insert(Order order) {
        orderRepository.insert(order);
    }
}
