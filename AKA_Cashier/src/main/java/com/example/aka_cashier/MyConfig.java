package com.example.aka_cashier;

import java.sql.*;

public class MyConfig {

    private static final String DB_URL = "jdbc:sqlite:db_products.db";

    private static Connection connect;
    private static Statement statement;
    private static ResultSet resultSet;

    public static void connection(){
        try{
            connect = DriverManager.getConnection(DB_URL);
            System.out.println("established");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void getDatabase(){

        MyConfig.connection();

        try{
            statement = connect.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM tb_products");

            while(resultSet.next()){
                System.out.println(
                        resultSet.getInt("id")+
                        resultSet.getString("name")+
                        resultSet.getInt("price")+
                        resultSet.getString("category")
                );
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) {
        getDatabase();
    }

}
