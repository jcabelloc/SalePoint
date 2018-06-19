package edu.tamu.jcabelloc.salepoint.ViewModel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import edu.tamu.jcabelloc.salepoint.data.repository.ProductRepository;

public class ProductDetailViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final ProductRepository productRepository;
    private final int id;
    public ProductDetailViewModelFactory(int id, ProductRepository productRepository) {
        this.id = id;
        this.productRepository = productRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        //return super.create(modelClass);
        return (T) new ProductDetailViewModel(id, productRepository);
    }
}
