package edu.tamu.jcabelloc.salepoint.ViewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import edu.tamu.jcabelloc.salepoint.data.local.entity.Order;
import edu.tamu.jcabelloc.salepoint.data.local.entity.OrderDetail;
import edu.tamu.jcabelloc.salepoint.data.repository.OrderDetailRepository;

public class OrderDetailsViewModel extends ViewModel {

    private LiveData<List<OrderDetail>> orderDetails;
    private OrderDetailRepository orderDetailRepository;

    public OrderDetailsViewModel(int orderId, OrderDetailRepository repository) {
        this.orderDetailRepository = repository;
        this.orderDetails = repository.getOrderDetailsByOrderId(orderId);
    }

    public LiveData<List<OrderDetail>> getOrderDetails() {
        return orderDetails;
    }

    public void insert(OrderDetail orderDetail) {
        orderDetailRepository.insert(orderDetail);
    }
}
