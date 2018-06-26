package edu.tamu.jcabelloc.salepoint.ViewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import edu.tamu.jcabelloc.salepoint.data.dto.ListViewPurchaseDetail;
import edu.tamu.jcabelloc.salepoint.data.local.entity.PurchaseDetail;
import edu.tamu.jcabelloc.salepoint.data.repository.PurchaseDetailRepository;

public class PurchaseDetailsViewModel extends ViewModel {

    private LiveData<List<ListViewPurchaseDetail>> purchaseDetails;
    private PurchaseDetailRepository purchaseDetailRepository;

    public PurchaseDetailsViewModel(int purchaseId, PurchaseDetailRepository purchaseDetailRepository) {
        this.purchaseDetails = purchaseDetailRepository.getExtendedPurchaseDetails(purchaseId);
        this.purchaseDetailRepository = purchaseDetailRepository;
    }

    public LiveData<List<ListViewPurchaseDetail>> getPurchaseDetails() {
        return purchaseDetails;
    }

    public void insert(PurchaseDetail purchaseDetail) {
        purchaseDetailRepository.insert(purchaseDetail);
    }
}
