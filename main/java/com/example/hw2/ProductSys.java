package com.example.hw2;

import java.util.ArrayList;
import java.util.Collections;

public class ProductSys {
    public static ArrayList<Product> products = new ArrayList<>();
    public static void prepareData(){
        Collections.addAll(products,
                new Product("Black Dress", "Dress", 25.50,R.drawable.blackdress),
                new Product("Red Dress", "Dress", 30,R.drawable.reddress),
                new Product("Blue Dress", "Dress", 60,R.drawable.bluedress),
                new Product("Blue Skirt", "Skirt", 20,R.drawable.blueskirt),
                new Product("Pink Top", "Top", 10,R.drawable.pinktop),
                new Product("White Skirt", "Skirt", 45,R.drawable.whiteskirt),
                new Product("Jean", "Bottom", 100,R.drawable.jean),
                new Product("Blue Top", "Top", 25,R.drawable.bluetop)
        );
    }
}
