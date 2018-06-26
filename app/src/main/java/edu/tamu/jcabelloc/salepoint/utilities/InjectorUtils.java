package edu.tamu.jcabelloc.salepoint.utilities;

import android.content.Context;

import edu.tamu.jcabelloc.salepoint.ViewModel.OrderDetailsViewModelFactory;
import edu.tamu.jcabelloc.salepoint.ViewModel.OrderInProgressViewModelFactory;
import edu.tamu.jcabelloc.salepoint.ViewModel.ProductDetailViewModelFactory;
import edu.tamu.jcabelloc.salepoint.ViewModel.ProductListViewModelFactory;
import edu.tamu.jcabelloc.salepoint.ViewModel.PurchaseDetailsViewModelFactory;
import edu.tamu.jcabelloc.salepoint.ViewModel.PurchaseInProgressViewModelFactory;
import edu.tamu.jcabelloc.salepoint.data.local.database.SaleAppDatabase;
import edu.tamu.jcabelloc.salepoint.data.repository.OrderDetailRepository;
import edu.tamu.jcabelloc.salepoint.data.repository.OrderRepository;
import edu.tamu.jcabelloc.salepoint.data.repository.ProductRepository;
import edu.tamu.jcabelloc.salepoint.data.repository.PurchaseDetailRepository;
import edu.tamu.jcabelloc.salepoint.data.repository.PurchaseRepository;

public class InjectorUtils {

    private static ProductRepository getProductRepository(Context context) {
        SaleAppDatabase saleAppDatabase = SaleAppDatabase.getInstance(context);
        return ProductRepository.getInstance(saleAppDatabase.productDao());
    }

    private static OrderRepository getOrderRepository(Context context) {
        SaleAppDatabase saleAppDatabase = SaleAppDatabase.getInstance(context);
        return OrderRepository.getInstance(saleAppDatabase.orderDao());
    }

    private static OrderDetailRepository getOrderDetailRepository(Context context) {
        SaleAppDatabase saleAppDatabase = SaleAppDatabase.getInstance(context);
        return OrderDetailRepository.getInstance(saleAppDatabase.orderDetailDao());
    }

    private static PurchaseRepository getPurchaseRepository(Context context) {
        SaleAppDatabase saleAppDatabase = SaleAppDatabase.getInstance(context);
        return PurchaseRepository.getInstance(saleAppDatabase.purchaseDao());
    }

    private static PurchaseDetailRepository getPurchaseDetailRepository(Context context) {
        SaleAppDatabase saleAppDatabase = SaleAppDatabase.getInstance(context);
        return PurchaseDetailRepository.getInstance(saleAppDatabase.purchaseDetailDao());
    }

    public static ProductListViewModelFactory getProductListViewModelFactory(Context context) {
        ProductRepository productRepository = getProductRepository(context.getApplicationContext());
        return new ProductListViewModelFactory(productRepository);
    }

    public static ProductDetailViewModelFactory getProductDetailViewModelFactory(Context context, int id){
        ProductRepository productRepository = getProductRepository(context.getApplicationContext());
        return new ProductDetailViewModelFactory(id, productRepository);
    }

    public static OrderInProgressViewModelFactory getOrderViewModelFactory(Context context, String user) {
        OrderRepository orderRepository = getOrderRepository(context.getApplicationContext());
        return new OrderInProgressViewModelFactory(user, orderRepository);
    }


    public static OrderDetailsViewModelFactory getOrderDetailViewModelFactory(Context context, int orderId) {
        OrderDetailRepository orderDetailRepository = getOrderDetailRepository(context.getApplicationContext());
        return new OrderDetailsViewModelFactory(orderId, orderDetailRepository);
    }

    public static PurchaseInProgressViewModelFactory getPurchaseInProgressViewModelFactory(Context context, String user) {
        PurchaseRepository purchaseRepository = getPurchaseRepository(context);
        return new PurchaseInProgressViewModelFactory(user, purchaseRepository);
    }


    public static PurchaseDetailsViewModelFactory getPurchaseDetailsViewModelFactory(Context context, int purchaseId) {
        PurchaseDetailRepository purchaseDetailRepository = getPurchaseDetailRepository(context);
        return new PurchaseDetailsViewModelFactory(purchaseId, purchaseDetailRepository);
    }
}
