package edu.tamu.jcabelloc.salepoint.data.local.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import edu.tamu.jcabelloc.salepoint.data.local.dao.OrderDao;
import edu.tamu.jcabelloc.salepoint.data.local.dao.OrderDetailDao;
import edu.tamu.jcabelloc.salepoint.data.local.dao.ProductDao;
import edu.tamu.jcabelloc.salepoint.data.local.dao.PurchaseDao;
import edu.tamu.jcabelloc.salepoint.data.local.dao.PurchaseDetailDao;
import edu.tamu.jcabelloc.salepoint.data.local.entity.Order;
import edu.tamu.jcabelloc.salepoint.data.local.entity.OrderDetail;
import edu.tamu.jcabelloc.salepoint.data.local.entity.Product;
import edu.tamu.jcabelloc.salepoint.data.local.entity.Purchase;
import edu.tamu.jcabelloc.salepoint.data.local.entity.PurchaseDetail;

@Database(entities = {Product.class, Order.class, OrderDetail.class, Purchase.class, PurchaseDetail.class}, version = 1)
public abstract class SaleAppDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "saleapp";
    public abstract ProductDao productDao();
    public abstract OrderDao orderDao();
    public abstract OrderDetailDao orderDetailDao();
    public abstract PurchaseDao purchaseDao();
    public abstract PurchaseDetailDao purchaseDetailDao();

    private static SaleAppDatabase instance;

    public static SaleAppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), SaleAppDatabase.class, DATABASE_NAME).build();
        }
        return instance;
    }
}
