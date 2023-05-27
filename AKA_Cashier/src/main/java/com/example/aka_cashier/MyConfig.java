package com.example.aka_cashier;

import java.sql.*;
import java.util.ArrayList;

public class MyConfig {

    private static final String DB_URL = "jdbc:sqlite:db_products.db";

    private static Connection connect;
    private static Statement statement;
    private static ResultSet resultSet;

    public static void connection(){
        try{
            connect = DriverManager.getConnection(DB_URL);
            System.out.println("DB connection established");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<String> getDatabaseCol(String colhead, String cat, boolean isSet, boolean isFil){

        MyConfig.connection();

        ArrayList<String> content = new ArrayList<>();

        if (isFil) {
            content.add("All");
        }

        try{
            statement = connect.createStatement();
            if (cat.equals("All")) {
                resultSet = statement.executeQuery("SELECT " + colhead + " FROM tb_products");
            } else {
                resultSet = statement.executeQuery("SELECT " + colhead + " FROM tb_products WHERE category LIKE '%" + cat + "%'");
            }

            while(resultSet.next()){
                if (!isSet) {
                    content.add(resultSet.getString(colhead));
                } else {
                    if (!content.contains(resultSet.getString(colhead))) {
                        content.add(resultSet.getString(colhead));
                    }
                }
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return content;
    }

    public static String getElmbyId(String i, String colhead) {

        MyConfig.connection();

        String elm = "null";

        try{
            statement = connect.createStatement();
            resultSet = statement.executeQuery("SELECT " + colhead + " FROM tb_products WHERE id = " + i);

//            elm = resultSet.getString(colhead);

            while(resultSet.next()){
                elm = resultSet.getString(colhead);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        return elm;
    }

    public static void main(String[] args) {
//        getDatabaseCol("category");
        getElmbyId("1", "name");
    }

}
