package edu.tamu.jcabelloc.salepoint.data.repository;

import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import edu.tamu.jcabelloc.salepoint.data.local.dao.PurchaseDao;
import edu.tamu.jcabelloc.salepoint.data.local.entity.Purchase;

public class PurchaseRepository {

    private static PurchaseRepository instance;
    private PurchaseDao purchaseDao;

    private PurchaseRepository(PurchaseDao purchaseDao) {
        this.purchaseDao = purchaseDao;
    }

    public static PurchaseRepository getInstance(PurchaseDao purchaseDetailDao){
        if(instance == null) {
            return new PurchaseRepository(purchaseDetailDao);
        }
        return instance;
    }

    public LiveData<Purchase> getPurchaseInProgress(String user) {
        return purchaseDao.getPurchaseByUserAndStatus(user, Purchase.STATUS_CREATED);
    }

    public void insert(Purchase purchase) {
        new InsertAsyncTask().execute(purchase);
    }

    private class InsertAsyncTask extends AsyncTask<Purchase, Void, Void> {

        @Override
        protected Void doInBackground(Purchase... purchases) {
            purchaseDao.insert(purchases[0]);
            return null;
        }
    }
}
