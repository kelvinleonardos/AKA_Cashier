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
        this.id = id;
        this.name = name;
        this.price = price;
        this.cat = cat;
        count=amount;
    }

    private int id;
    private String name;
    private int price;
    private String cat;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getCat() {
        return cat;
    }

}
