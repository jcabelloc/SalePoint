package edu.tamu.jcabelloc.salepoint.data.repository;


import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import edu.tamu.jcabelloc.salepoint.data.dto.ListViewPurchaseDetail;
import edu.tamu.jcabelloc.salepoint.data.local.dao.PurchaseDetailDao;
import edu.tamu.jcabelloc.salepoint.data.local.entity.PurchaseDetail;

public class PurchaseDetailRepository {

    private static PurchaseDetailRepository instance;
    PurchaseDetailDao purchaseDetailDao;
    PurchaseDetailRepository(PurchaseDetailDao purchaseDetailDao) {
        this.purchaseDetailDao = purchaseDetailDao;
    }

    public static PurchaseDetailRepository getInstance(PurchaseDetailDao purchaseDetailDao){
        if(instance == null) {
            return new PurchaseDetailRepository(purchaseDetailDao);
        }
        return instance;
    }

    public LiveData<List<ListViewPurchaseDetail>> getExtendedPurchaseDetails(int purchaseId) {
        return purchaseDetailDao.getExtendedPurchaseDetails(purchaseId);
    }

    public void insert(PurchaseDetail purchaseDetail) {
        new InserAsyncTask().execute(purchaseDetail);
    }

    private class InserAsyncTask extends AsyncTask<PurchaseDetail, Void, Void> {

        @Override
        protected Void doInBackground(PurchaseDetail... purchaseDetails) {
            purchaseDetailDao.insert(purchaseDetails[0]);
            return null;
        }
    }
}
