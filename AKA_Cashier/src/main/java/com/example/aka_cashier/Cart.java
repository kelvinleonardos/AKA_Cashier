package com.example.aka_cashier;

import java.util.ArrayList;

public class Cart {

    private static ArrayList<Product> p = new ArrayList<>();
    private static ArrayList<String> p_name = new ArrayList<>();


    static ArrayList<Product> getP(){
        return p;
    }

    static void add_product(int id, String name, int price, String cat) {
        if (!p_name.contains(name)){
            Product product = new Product(id, name, price, cat);
            p.add(product);
            p_name.add(product.getName());
        } else {
            for (Product i : p) {
                if (i.getName().equals(name)) {
                    i.addCount();
                }
            }
        }
    }

    static int count(Product pr) {
        int amount;

        ArrayList<String> p_name = new ArrayList<>();

        for (Product i : p){
            p_name.add(i.getName());
        }

        if (p_name.contains(pr.getName())) {
            amount = pr.getCount();
        } else {
            amount = 1;
        }
        return amount;
    }

    static int calc_total() {
        int sum=0;
        for (Product i:p) {
            sum+=i.getPrice()*i.getCount();
        }
        return sum;
    }
}
