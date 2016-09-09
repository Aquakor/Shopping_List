package com.example.shoppinglist;

import android.os.Parcel;
import android.os.Parcelable;

public class Product implements Parcelable{
    String mProduct;

    public Product(String productName){
        this.mProduct = productName;
    }

    private Product(Parcel in) {mProduct = in.readString();}

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeString(mProduct);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
}
