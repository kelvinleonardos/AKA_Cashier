package com.example.aka_cashier;

import java.util.ArrayList;

public class Cart {

    private static ArrayList<Product> p = new ArrayList<>();

    static ArrayList<Product> getP(){
        return p;
    }

    static void add_product(int id, String name, int price, String cat) {
        Product product = new Product(id, name, price, cat);
        p.add(product);
    }

    static int calc_total() {
        int sum=0;
        for (Product i:p) {
            sum+=i.getPrice();
        }
        return sum;
    }
}
