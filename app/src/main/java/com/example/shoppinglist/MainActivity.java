package com.example.shoppinglist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    /** Tag for log messages */
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    /** Adapter for the list of products */
    private SingleListAdapter mAdapter;

    /** ArrayList for Placeholder name of products */
    private ArrayList<Product> mProductList;

    private AutoCompleteTextView actv;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        String[] products = getResources().getStringArray(R.array.products);
        actv = (AutoCompleteTextView) findViewById(R.id.product_edit_text);
        ArrayAdapter<String> editTextAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1,products);
        actv.setAdapter(editTextAdapter);

        ListView listView = (ListView) findViewById(R.id.product_list_view);

        // Make new list for products
        mAdapter = new SingleListAdapter(this, new ArrayList<Product>());

        listView.setAdapter(mAdapter);

        /**
         * When add button is pushed it adds product name
         * from AutoCompleteTextView into mAdapter.
         * When the AutoCompleteTextView is empty it
         * returns Toast message...
         */
        Button add = (Button) findViewById(R.id.add_button);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(actv.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this, "Enter product name!", Toast.LENGTH_SHORT).show();
                }else{
                    mAdapter.add(new Product(actv.getText().toString()));
                    actv.setText("");
                }

            }
        });

    }
}
