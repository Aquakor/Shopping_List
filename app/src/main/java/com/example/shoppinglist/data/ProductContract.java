package com.example.shoppinglist.data;

import android.provider.BaseColumns;

public final class ProductContract {

    private ProductContract(){}

    public static final class ProductEntry implements BaseColumns {

        public final static String TABLE_NAME = "products";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_PRODUCT_NAME = "name";
    }
}
