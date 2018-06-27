package edu.tamu.jcabelloc.salepoint.provider;

import android.app.SearchManager;
import android.arch.lifecycle.ViewModelProviders;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.List;

import edu.tamu.jcabelloc.salepoint.ViewModel.ProductListViewModel;
import edu.tamu.jcabelloc.salepoint.ViewModel.ProductListViewModelFactory;
import edu.tamu.jcabelloc.salepoint.data.dto.ListViewProduct;
import edu.tamu.jcabelloc.salepoint.data.local.database.SaleAppDatabase;
import edu.tamu.jcabelloc.salepoint.data.repository.ProductRepository;
import edu.tamu.jcabelloc.salepoint.utilities.InjectorUtils;

public class SearchProductProvider extends ContentProvider {

    @Override
    public boolean onCreate() {
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {


        ProductRepository productRepository = InjectorUtils.getProductRepository(getContext().getApplicationContext());
        List<ListViewProduct> products = productRepository.getProductsByName("%" + selectionArgs[0] + "%");

        String[] columNames = {BaseColumns._ID, SearchManager.SUGGEST_COLUMN_TEXT_1, SearchManager.SUGGEST_COLUMN_TEXT_2};
        MatrixCursor cursor = new MatrixCursor(columNames);
        for (ListViewProduct product: products) {
            cursor.addRow(new Object[]{String.valueOf(product.getId()), product.getName(), product.getDescription()});
        }

        //return null;
        Log.d("JCC", "selection: " + selection);
        Log.d("JCC", "selectionArgs: "+ selectionArgs[0]);
        Log.d("JCC", "uri.getLastPathSegment(): " + uri.getLastPathSegment());


        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
