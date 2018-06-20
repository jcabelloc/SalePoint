package edu.tamu.jcabelloc.salepoint.data.repository;

import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import edu.tamu.jcabelloc.salepoint.data.local.dao.OrderDao;
import edu.tamu.jcabelloc.salepoint.data.local.entity.Order;

public class OrderRepository {

    private OrderDao orderDao;
    private static OrderRepository instance;

    private OrderRepository(OrderDao orderDao) {
        this.orderDao = orderDao;
    }
    public static OrderRepository getInstance(OrderDao orderDao){
        if (instance == null ) {
            instance = new OrderRepository(orderDao);
        }
        return instance;
    }

    public LiveData<Order> getOrder(int orderId) {
        return orderDao.getOrder(orderId);
    }

    public void insert(Order order) {
        new InsertAsyncTask().execute(order);
    }

    public LiveData<Order> getOrderInProgress(String user) {
        return orderDao.getOrderByUserAndStatus(user, Order.STATUS_CREATED);
    }

    private class InsertAsyncTask extends AsyncTask<Order, Void, Void> {
        @Override
        protected Void doInBackground(Order... orders) {
            orderDao.insert(orders[0]);
            return null;
        }
    }


}
