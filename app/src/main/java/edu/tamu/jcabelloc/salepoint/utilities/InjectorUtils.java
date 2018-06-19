package edu.tamu.jcabelloc.salepoint.utilities;

import android.content.Context;
import android.util.Log;

import edu.tamu.jcabelloc.salepoint.ViewModel.ProductDetailViewModelFactory;
import edu.tamu.jcabelloc.salepoint.ViewModel.ProductListViewModelFactory;
import edu.tamu.jcabelloc.salepoint.data.local.dao.ProductDao;
import edu.tamu.jcabelloc.salepoint.data.local.database.SaleAppDatabase;
import edu.tamu.jcabelloc.salepoint.data.repository.ProductRepository;

public class InjectorUtils {

    private static ProductRepository getProductRepository(Context context) {
        SaleAppDatabase saleAppDatabase = SaleAppDatabase.getInstance(context);
        return ProductRepository.getInstance(saleAppDatabase.productDao());

    }

    public static ProductListViewModelFactory getProductListViewModelFactory(Context context) {
        ProductRepository productRepository = getProductRepository(context.getApplicationContext());
        return new ProductListViewModelFactory(productRepository);
    }

    public static ProductDetailViewModelFactory getProductDetailViewModelFactory(Context context, int id){
        ProductRepository productRepository = getProductRepository(context.getApplicationContext());
        return new ProductDetailViewModelFactory(id, productRepository);
    }
}
