package com.example.myhomecare.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.myhomecare.model.CartModel;

import java.util.ArrayList;
import java.util.List;

public class CartSqliteHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "MyCartDB";
    public static String TABLE_NAME = "MyOrders";

    public CartSqliteHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table " + TABLE_NAME + "(id integer primary key, product_id integer,product_name text,product_brand text,product_shop text,product_size text,product_price float,product_percentOff float,product_offerPrice float,product_quantity integer,product_image text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    private boolean updateCartProduct(CartModel cartModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("product_quantity", cartModel.getQuantity());
        db.update(TABLE_NAME, contentValues, "product_id=?", new String[]{Integer.toString(cartModel.getProductId())});
        db.close();

        Log.d("updateCartPage",""+cartModel.getProductId()+" "+cartModel.getQuantity());
        return true;
    }


    public boolean insertProduct(CartModel cartModel, Context context) {
        if (getData(cartModel.getProductId()) != 0) {
            if (updateContact(cartModel, getData(cartModel.getProductId()))) {
                 Toast.makeText(context, "Item Updated", Toast.LENGTH_SHORT).show();
            }
        } else {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("product_id", cartModel.getProductId());
            contentValues.put("product_name", cartModel.getProductName());
            contentValues.put("product_brand",cartModel.getProductBrandName());
            contentValues.put("product_shop",cartModel.getShopName());
            contentValues.put("product_size",cartModel.getSize());
            contentValues.put("product_price", cartModel.getPrice());
            contentValues.put("product_percentOff",cartModel.getPercentageOff());
            contentValues.put("product_offerPrice",cartModel.getOfferPrice());
            contentValues.put("product_quantity", cartModel.getQuantity());
            contentValues.put("product_image",cartModel.getImage());

            db.insert(TABLE_NAME, null, contentValues);
            Log.d("ProductId", cartModel.toString());
            Toast.makeText(context, cartModel.getQuantity() + " " + cartModel.getProductBrandName()+ " Added to Cart !!", Toast.LENGTH_LONG).show();
            db.close();
        }
        return true;
    }

    public boolean cartInsertProduct(CartModel model,Context context){
        if(updateCartProduct(model)){

        }else{

        }
        return true;
    }

    public boolean updateContact(CartModel model, int data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("product_quantity", (data + model.getQuantity()));
        db.update(TABLE_NAME, contentValues, "product_id = ? ", new String[]{Integer.toString(model.getProductId())});
        db.close();
        Log.d("CalledForUpdate", "" +(data + model.getQuantity()));

        return true;
    }

    public int numberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME);
        db.close();
        return numRows;
    }

    public List<CartModel> getAllProducts() {
        List<CartModel> array_list = new ArrayList<>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            CartModel cartModel = new CartModel();
            cartModel.setProductId(res.getInt(res.getColumnIndex("product_id")));
            cartModel.setProductName(res.getString(res.getColumnIndex("product_name")));
            cartModel.setProductBrandName(res.getString(res.getColumnIndex("product_brand")));
            cartModel.setShopName(res.getString(res.getColumnIndex("product_shop")));
            cartModel.setSize(res.getString(res.getColumnIndex("product_size")));
            cartModel.setPrice(res.getFloat(res.getColumnIndex("product_price")));
            cartModel.setPercentageOff(res.getFloat(res.getColumnIndex("product_percentOff")));
            cartModel.setOfferPrice(res.getFloat(res.getColumnIndex("product_offerPrice")));
            cartModel.setQuantity(res.getInt(res.getColumnIndex("product_quantity")));
            cartModel.setImage(res.getString(res.getColumnIndex("product_image")));
            array_list.add(cartModel);
            Log.d("ProductId", cartModel.toString());
            res.moveToNext();
        }
        db.close();
        return array_list;
    }

    public int getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME + " where product_id=" + id, null);
        if (res.moveToFirst()) {
            return res.getInt(res.getColumnIndex("product_quantity"));
        } else {
            db.close();
            return 0;
        }


    }

    public Integer deleteProduct(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,
                "product_id = ? ",
                new String[]{Integer.toString(id)});
    }
}
