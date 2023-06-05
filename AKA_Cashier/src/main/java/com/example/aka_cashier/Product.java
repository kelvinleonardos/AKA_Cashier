package com.example.aka_cashier;

public class Product extends Aproduct {

    private int count;

    public void addCount(int amount) {
        count+=amount;
    }

    public int getCount() {
        return count;
    }

    public Product(int id, String name, int price, String cat, int amount) {
        super(id, name, price, cat);
        count=amount;
    }

}
