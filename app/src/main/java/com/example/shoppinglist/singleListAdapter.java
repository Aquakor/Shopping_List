package com.example.shoppinglist;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Kornel on 07.09.2016.
 */
public class SingleListAdapter extends ArrayAdapter<Product> {

    /** Tag for log messages **/
    private static final String LOG_TAG = SingleListAdapter.class.getSimpleName();

    private String mProduct;

    public SingleListAdapter(Activity context, ArrayList<Product> list) {
        super(context, 0, list);
        Log.i(LOG_TAG,"SingleListAdapter");
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
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
                remove(currentProduct);
            }
        });


        Log.i("SingleListAdapter", "It's working?" + currentProduct);

        return listItemView;
    }
}
