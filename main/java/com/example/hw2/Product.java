package com.example.hw2;

import android.widget.ImageView;

public class Product {
    private String name;
    private String description;
    private double price;
    //yeni
    private int imageResId;
    public static int numberofproducts = 0;


    public Product(String name, String description, double price, int imageResId) {
        this.name = name;
        this.description = description;
        this.price = price;
        //yeni
        this.imageResId = imageResId;
        numberofproducts++;
    }

    @Override
    public String toString() {
        return "Product Name : " + name + "\n" + "Description : " + description + "\n" +
                "Price : " + price + "\n";
    }
    public String getName() {return name;}
    public void setName(String name) {this.name=name;}
    //public void setImgProductItem() {this.imgProductItem=imgProductItem;}
    //yeni
    public int getImageResId() {
        return imageResId;
    }
    public String getDescription() {return description;}
    public void setDescription(String description) {this.description=description;}
    public double getPrice() {return price;}
    public void setPrice(double price) {this.price = price;}
    public static int getNumberofproducts() {
        return numberofproducts;
    }

    public static void setNumberofproducts(int numberofproducts) {
        Product.numberofproducts = numberofproducts;
    }
}
