package com.example.aka_cashier;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Cart {

    private static ArrayList<Product> p = new ArrayList<>();
    private static ArrayList<String> p_name = new ArrayList<>();

    static ArrayList<Product> getP(){
        return p;
    }

    static ArrayList<String> getPn(){
        return p_name;
    }

    static void clear_cart() {
        p.clear();
        p_name.clear();
    }

    static void add_product(int id, String name, int price, String cat, int amount) {
        if (!p_name.contains(name)){
            Product product = new Product(id, name, price, cat, amount);
            p.add(product);
            p_name.add(product.getName());
        } else {
            for (Product i : p ) {
                if (i.getName().equals(name) && amount<=Integer.parseInt(MyConfig.getElmbyId(String.valueOf(id), "stock" ))-amount) {
                    i.addCount(amount);
                } else {
                    Text tomax = new Text("The Product Amount Kebanyakan!");

                    StackPane tomaxst = new StackPane(tomax);
                    tomaxst.setPrefSize(200, 100);

                    Button buok = new Button("Ok");
                    buok.setPrefSize(70, 25);

                    VBox tom = new VBox(tomaxst, buok);
                    tom.setAlignment(Pos.CENTER);

                    Scene keb = new Scene(tom, 250, 140);

                    Stage kebs = new Stage();
                    kebs.setScene(keb);
                    kebs.setTitle("AKA CASHIER");
                    kebs.setResizable(false);
                    kebs.show();

                    buok.setOnAction(event39 -> {
                        kebs.close();
                    });
                }
            }
        }
    }

//    static int count(Product pr) {
//        int amount;
//
//        ArrayList<String> p_name = new ArrayList<>();
//
//        for (Product i : p){
//            p_name.add(i.getName());
//        }
//
//        if (p_name.contains(pr.getName())) {
//            amount = pr.getCount();
//        } else {
//            amount = 1;
//        }
//        return amount;
//    }

    static int calc_total() {
        int sum=0;
        for (Product i:p) {
            sum+=i.getPrice()*i.getCount();
        }
        return sum;
    }
}
