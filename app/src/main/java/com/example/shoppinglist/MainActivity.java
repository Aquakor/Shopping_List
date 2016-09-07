package com.example.shoppinglist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    /** Tag for log messages */
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    /** Adapter for the list of products */
    private SingleListAdapter mAdapter;

    /** ArrayList for Placeholder name of products */
    private ArrayList<Product> mProductList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.product_list_view);

        // Make new list for products
        mProductList = new ArrayList<Product>();

        mProductList.add(new Product("Mleko"));
        mProductList.add(new Product("Masło"));

        mAdapter = new SingleListAdapter(this, mProductList);

        listView.setAdapter(mAdapter);

        Button add = (Button) findViewById(R.id.add_button);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //EditText temp = (EditText) findViewById(R.id.product_edit_text);
                //mAdapter.add(new Product(temp.getText().toString()));
                //temp.setText("");
                mProductList.remove(0);
                mAdapter.notifyDataSetChanged();
            }
        });

    }

    public Void removeProduct(int position){
        mProductList.remove(position);
        mAdapter.notifyDataSetChanged();
        return null;
    }
}
