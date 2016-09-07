package com.example.shoppinglist;

/**
 * Created by Kornel on 07.09.2016.
 */
public class Product {
    String mProduct;

    public Product(String productName){
        mProduct = productName;
    }

    public String getProductName(){
        return mProduct;
    }
}
