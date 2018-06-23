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

    public void addOrderDetailToCart(OrderDetail orderDetail) {
        // TODO Refactor this part
        orderDetail.setQuantity(1);
        orderDetail.setUnitPrice(100);
        orderDetail.setSubTotal(100);
        new InsertAsyncTask().execute(orderDetail);
    }

    private class InsertAsyncTask extends AsyncTask<OrderDetail, Void, Void> {
        @Override
        protected Void doInBackground(OrderDetail... orderDetails) {
            orderDetailDao.insert(orderDetails[0]);
            return null;
        }
    }

    public void deleteOrderDetail(int orderDetailId){
        new DeleteAsyncTask().execute(orderDetailId);
    }

    private class DeleteAsyncTask extends AsyncTask<Integer, Void, Void> {
        @Override
        protected Void doInBackground(Integer... ids) {
            orderDetailDao.delete(ids[0]);
            return null;
        }
    }

    public void addQuantityByOne(int orderDetailId) {
        new UpdateQuantityAsyncTask().execute(orderDetailId, 1);
    }

    public void subtractQuantityByOne(int orderDetailId) {
        new UpdateQuantityAsyncTask().execute(orderDetailId, -1);
    }

    private class UpdateQuantityAsyncTask extends AsyncTask<Integer, Void, Void> {

        @Override
        protected Void doInBackground(Integer... values) {
            orderDetailDao.updateQuantity(values[0], values[1]);
            return null;
        }
    }

}
