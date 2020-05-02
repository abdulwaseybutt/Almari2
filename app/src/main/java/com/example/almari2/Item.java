package com.example.almari2;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Item implements Serializable {

    public Integer Id;
    public String Name;
    public String ImageURL;
    public Integer Price;

    public void setPrice(Integer price) {
        Price = price;
    }

    public Item() {
    }

    public Integer getPrice() {
        return Price;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setName(String name) {
        Name = name;
    }

    public Item(int id, String name,String imageURL, Integer price) {
        Id = id;
        Name = name;
        ImageURL = imageURL;
        Price = price;
    }

    public void setImageURL(String imageURL) {
        ImageURL = imageURL;
    }

    public int getId() {

        return Id;
    }

    public String getName() {
        return Name;
    }

    public String getImageURL() {
        return ImageURL;
    }
    private Item(Parcel in) {
        this.Name=in.readString();
        this.Id = in.readInt();
        this.ImageURL= in.readString();
        this.Price=in.readInt();
    }
}
