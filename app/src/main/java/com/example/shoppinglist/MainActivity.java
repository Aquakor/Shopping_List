package com.example.shoppinglist;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.shoppinglist.data.ProductContract.ProductEntry;
import com.example.shoppinglist.data.ProductDbHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    /**
     * Database helper for list of products
     */
    private ProductDbHelper mDbHelper = new ProductDbHelper(this);

    /**
     * Tag for log messages
     */
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    /**
     * Adapter for the list of products
     */
    private SingleListAdapter mAdapter;

    /**
     * ArrayList for the product names
     */
    private ArrayList<Product> productArrayList;

    private AutoCompleteTextView autoCompleteTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(LOG_TAG, " -asd" + savedInstanceState);
        if (savedInstanceState == null || savedInstanceState.containsKey("products")) {
            productArrayList = new ArrayList<>();
            mAdapter = new SingleListAdapter(this, productArrayList);
            displayDatabaseInfo();
        } else {
            productArrayList = savedInstanceState.getParcelableArrayList("products");
            Log.i(LOG_TAG, "Succesfully load info from onSaveInstance");
        }

        // Get list of suggested product names for actv
        String[] products = getResources().getStringArray(R.array.products);
        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.product_edit_text);
        ArrayAdapter<String> editTextAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, products);
        autoCompleteTextView.setAdapter(editTextAdapter);


        ListView listView = (ListView) findViewById(R.id.product_list_view);

        // Make new list for products
        productArrayList = new ArrayList<>();

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
                // Get text from autoCompleteTextView
                String temp = autoCompleteTextView.getText().toString();
                if (temp.equals("")) {
                    Toast.makeText(MainActivity.this, "Enter product name!", Toast.LENGTH_SHORT).show();
                } else {
                    // Gets String from autoCompleteTextView and parse it into database
                    insertProduct(temp);

                    mAdapter.clear();
                    // Update the listView
                    displayDatabaseInfo();

                    //mAdapter.add(new Product(autoCompleteTextView.getText().toString()));
                    autoCompleteTextView.setText("");
                }

            }
        });

    }

    private void insertProduct(String nameOfProduct) {
        // Gets the data repository in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a new map of values
        ContentValues values = new ContentValues();
        values.put(ProductEntry.COLUMN_PRODUCT_NAME, nameOfProduct);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(ProductEntry.TABLE_NAME, null, values);

        Log.i(LOG_TAG, "New row id: " + newRowId);
    }

    private void displayDatabaseInfo() {
        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                ProductEntry._ID,
                ProductEntry.COLUMN_PRODUCT_NAME
        };

        Cursor cursor = db.query(
                ProductEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        try {
            // Figure out the index of each column
            int nameColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_PRODUCT_NAME);

            // Iterate through all the returned rows in the cursor and attempt to add them
            // into mAdapter
            while (cursor.moveToNext()) {
                String currentName = cursor.getString(nameColumnIndex);

                mAdapter.add(new Product(currentName));
            }
        } finally {
            cursor.close();
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList("products", productArrayList);
        Log.i(LOG_TAG, "SavedInstance = " + productArrayList);
        super.onSaveInstanceState(outState);
    }


}
