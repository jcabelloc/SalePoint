package edu.tamu.jcabelloc.salepoint.ViewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import edu.tamu.jcabelloc.salepoint.data.local.entity.Product;
import edu.tamu.jcabelloc.salepoint.data.repository.ProductRepository;

public class ProductDetailViewModel extends ViewModel {

    LiveData<Product> product;
    public ProductDetailViewModel(int id, ProductRepository repository){
        this.product = repository.getProduct(id);
    }

    public LiveData<Product> getProduct(int id) {
        return product;
    }
}
