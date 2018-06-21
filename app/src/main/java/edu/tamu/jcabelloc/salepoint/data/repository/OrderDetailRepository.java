package edu.tamu.jcabelloc.salepoint.data.repository;

import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import edu.tamu.jcabelloc.salepoint.data.local.dao.OrderDetailDao;
import edu.tamu.jcabelloc.salepoint.data.local.entity.OrderDetail;

public class OrderDetailRepository {

    private static OrderDetailRepository instance;
    private OrderDetailDao orderDetailDao;

    private OrderDetailRepository(OrderDetailDao orderDetailDao) {
        this.orderDetailDao = orderDetailDao;
    }

    public static OrderDetailRepository getInstance(OrderDetailDao orderDetailDao) {
        if (instance == null) {
            instance = new OrderDetailRepository(orderDetailDao);
        }
        return instance;
    }

    public LiveData<List<OrderDetail>> getOrderDetailsByOrderId(int orderId) {
        return orderDetailDao.getOrderDetailsByOrderId(orderId);
    }

    public void insert(OrderDetail orderDetail) {
        new InsertAsyncTask().execute(orderDetail);
    }

    private class InsertAsyncTask extends AsyncTask<OrderDetail, Void, Void> {
        @Override
        protected Void doInBackground(OrderDetail... orderDetails) {
            orderDetailDao.insert(orderDetails[0]);
            return null;
        }
    }
}
