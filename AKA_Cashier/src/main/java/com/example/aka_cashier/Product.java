package com.example.aka_cashier;

public class Product {

    public Product(int id, String name, int price, String cat) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.cat = cat;
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
