package com.example.shoppinglist;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.shoppinglist.data.ProductContract.ProductEntry;
import com.example.shoppinglist.data.ProductDbHelper;

import java.util.ArrayList;

/**
 * Created by Kornel on 07.09.2016.
 */
public class SingleListAdapter extends ArrayAdapter<Product> {

    /** Database helper for list of products */
    private ProductDbHelper mDbHelper = new ProductDbHelper(getContext());

    /** Tag for log messages **/
    private static final String LOG_TAG = SingleListAdapter.class.getSimpleName();

    private String mProduct;

    public SingleListAdapter(Activity context, ArrayList<Product> list) {
        super(context, 0, list);
        Log.i(LOG_TAG,"SingleListAdapter");
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.product_list, parent, false);
        }
        final Product currentProduct = getItem(position);
        Log.i(LOG_TAG,currentProduct.getProductName() + "  --  " + position);
        final TextView productNameTextView = (TextView) listItemView.findViewById(R.id.product_text_view);

        productNameTextView.setText(currentProduct.getProductName());

        CheckBox checkBox = (CheckBox) listItemView.findViewById(R.id.product_checkbox);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = mDbHelper.getWritableDatabase();
                Log.i(LOG_TAG,"Current position = " + position);
                db.delete(ProductEntry.TABLE_NAME, ProductEntry.COLUMN_PRODUCT_NAME + " = \"" + currentProduct.getProductName() + "\"", null);
                remove(currentProduct);

            }
        });

        return listItemView;
    }
}
