package edu.tamu.jcabelloc.salepoint.ViewModel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import edu.tamu.jcabelloc.salepoint.data.repository.ProductRepository;

public class ProductListViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final ProductRepository productRepository;
    public ProductListViewModelFactory(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ProductListViewModel(productRepository);
    }
}
