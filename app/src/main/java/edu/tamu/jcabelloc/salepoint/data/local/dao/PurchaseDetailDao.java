package edu.tamu.jcabelloc.salepoint.data.local.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import edu.tamu.jcabelloc.salepoint.data.dto.ListViewPurchaseDetail;
import edu.tamu.jcabelloc.salepoint.data.local.entity.PurchaseDetail;

@Dao
public interface PurchaseDetailDao {
    @Query("SELECT det.purchaseId, det.purchaseDetailId, det.productId, prod.name as productName, prod.description as productDescription, "
            + "prod.image, det.quantity, det.unitCost, det.subTotal "
            + "FROM PurchaseDetail det, Product prod WHERE det.productId = prod.id AND det.purchaseId = :purchaseId ")
    LiveData<List<ListViewPurchaseDetail>> getExtendedPurchaseDetails(int purchaseId);

    @Insert
    void insert(PurchaseDetail purchaseDetail);
}
