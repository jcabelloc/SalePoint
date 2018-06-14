package edu.tamu.jcabelloc.salepoint.data.local.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import edu.tamu.jcabelloc.salepoint.data.local.dao.ProductDao;
import edu.tamu.jcabelloc.salepoint.data.local.entity.Product;

@Database(entities = {Product.class}, version = 1)
public abstract class SaleAppDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "saleapp";
    public abstract ProductDao productDao();

    //TODO make this class singleton
    public static SaleAppDatabase getInstance(Context context) {
        SaleAppDatabase db = Room.databaseBuilder(context.getApplicationContext(), SaleAppDatabase.class, DATABASE_NAME).build();
        return db;
    }

}
