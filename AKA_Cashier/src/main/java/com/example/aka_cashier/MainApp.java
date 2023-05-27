package com.example.aka_cashier;
import javafx.animation.PauseTransition;
import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainApp extends Application {

    private Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {

        stage = primaryStage;

        stage.setScene(getScene1());
        stage.setTitle("AKA CASHIER");
        stage.setResizable(false);
        stage.show();

    }

    // scene 1: app logo
    private Scene getScene1(){

        ImageView logo = new ImageView("images/logo.png");
        logo.setPreserveRatio(true);
        logo.setFitWidth(200);

        StackPane opening = new StackPane(logo);
        opening.setPrefSize(400, 225);

        Scene scene = new Scene(opening);

        PauseTransition delay = new PauseTransition(Duration.seconds(1));
        delay.setOnFinished(v -> {
            stage.setScene(getScene2());
        });
        delay.play();

        return scene;

    }

    // scene 2: main page
    private Scene getScene2(){

         /*
         - upper left vbox
         - lower left vbox
         - left main section
         - upper right vbox
         - lower right vbox
         - right main section
         - hbox: join left and right section
         */

        // upper left vbox
        Text ulv_title = new Text("Products List");

            // filter section
            Text ulv_filter_text = new Text("Filter:");
            ComboBox<String> ulv_filter_box = new ComboBox<>();

                ulv_filter_box.setItems(FXCollections.observableArrayList(MyConfig.getDatabaseCol("category", "All", true, true)));

            // scrollpane secton
            ScrollPane ulv_scroll = new ScrollPane();
            ulv_scroll.setPrefSize(350, 180);

                VBox usv = new VBox();

        HBox ulv_filter = new HBox(ulv_filter_text, ulv_filter_box);

        VBox ulv = new VBox(ulv_title, ulv_filter, ulv_scroll);
        ulv.setPrefHeight(250);

//============================================================

        // lower left vbox
        Text llv_title = new Text("Details");

            Label llv_id_text = new Label("Id:");
            llv_id_text.setPrefWidth(80);
            TextField llv_id_field = new TextField();

        HBox llv_id = new HBox(llv_id_text, llv_id_field);

            Label llv_name_text = new Label("Name:");
            llv_name_text.setPrefWidth(80);
            TextField llv_name_field = new TextField();

        HBox llv_name = new HBox(llv_name_text, llv_name_field);

            Label llv_price_text = new Label("Price:");
            llv_price_text.setPrefWidth(80);
            TextField llv_price_field = new TextField();

        HBox llv_price = new HBox(llv_price_text, llv_price_field);

            Label llv_cat_text = new Label("Category:");
            llv_cat_text.setPrefWidth(80);
            TextField llv_cat_field = new TextField();

        HBox llv_cat = new HBox(llv_cat_text, llv_cat_field);

            Button llv_add = new Button("Add to Cart");

            Button llv_reset = new Button("Reset");

        HBox llv_button = new HBox(llv_add, llv_reset);

            for (String i:MyConfig.getDatabaseCol("id", "All", false, false)) {

                Label lid = new Label(MyConfig.getElmbyId(i, "id"));
                lid.setPrefSize(40, 20);
                Label lname = new Label(MyConfig.getElmbyId(i, "name"));
                lname.setPrefSize(120, 20);
                Label lprice = new Label(MyConfig.getElmbyId(i, "price"));
                lprice.setPrefSize(120, 20);
                Label lcat = new Label(MyConfig.getElmbyId(i, "category"));
                lcat.setPrefSize(100, 20);

                HBox h = new HBox(lid, lname, lprice, lcat);

                Button b = new Button();

                b.setGraphic(h);

                b.setOnAction(event -> {
                    llv_id_field.setText(MyConfig.getElmbyId(i, "id"));
                    llv_name_field.setText(MyConfig.getElmbyId(i, "name"));
                    llv_price_field.setText(MyConfig.getElmbyId(i, "price"));
                    llv_cat_field.setText(MyConfig.getElmbyId(i, "category"));
                });

                usv.getChildren().add(b);

            }
            ulv_scroll.setContent(usv);

            ulv_filter_box.setOnAction(event -> {
                usv.getChildren().clear();
                for (String i:MyConfig.getDatabaseCol("id", ulv_filter_box.getValue(), false, false)) {

                    Label lid = new Label(MyConfig.getElmbyId(i, "id"));
                    lid.setPrefSize(40, 20);
                    Label lname = new Label(MyConfig.getElmbyId(i, "name"));
                    lname.setPrefSize(120, 20);
                    Label lprice = new Label(MyConfig.getElmbyId(i, "price"));
                    lprice.setPrefSize(120, 20);
                    Label lcat = new Label(MyConfig.getElmbyId(i, "category"));
                    lcat.setPrefSize(100, 20);

                    HBox h = new HBox(lid, lname, lprice, lcat);

                    Button b = new Button();

                    b.setGraphic(h);

                    b.setOnAction(ev -> {
                        llv_id_field.setText(MyConfig.getElmbyId(i, "id"));
                        llv_name_field.setText(MyConfig.getElmbyId(i, "name"));
                        llv_price_field.setText(MyConfig.getElmbyId(i, "price"));
                        llv_cat_field.setText(MyConfig.getElmbyId(i, "category"));
                    });

                    usv.getChildren().add(b);

                }
                ulv_scroll.setContent(usv);
            });

        VBox llv = new VBox(llv_title, llv_id, llv_name, llv_price, llv_cat, llv_button);
        llv.setPrefHeight(300);

//============================================================

        // left main section
        VBox lms = new VBox(ulv, llv);
        lms.setPrefWidth(400);

//============================================================

        // upper right vbox
        Text urv_title = new Text("Buying List");

        ScrollPane urv_scroll = new ScrollPane();

        VBox urv_scroll_v = new VBox();

        VBox urv = new VBox(urv_title, urv_scroll);
        urv.setPrefHeight(250);

//============================================================

        // lower right vbox
        Text lrv_title = new Text("Details");

            Text lrv_tprice_text = new Text("Total Price:");
            Label lrv_tprice_label = new Label();

        HBox lrv_tprice = new HBox(lrv_tprice_text, lrv_tprice_label);

            Text lrv_disc_text = new Text("Discount:");
            Label lrv_disc_label = new Label();

        HBox lrv_disc = new HBox(lrv_disc_text, lrv_disc_label);

            Text lrv_tpay_text = new Text("Total Pay:");
            Label lrv_tpay_label = new Label();

        HBox lrv_tpay = new HBox(lrv_tpay_text, lrv_tpay_label);

        llv_add.setOnAction(event -> {
            urv_scroll_v.getChildren().clear();
            Cart.add_product(Integer.parseInt(llv_id_field.getText()), llv_name_field.getText(), Integer.parseInt(llv_price_field.getText()), llv_cat_field.getText());
            for (Product p : Cart.getP()){
                Label l1 = new Label();
                l1.setPrefWidth(80);
                Label l2 = new Label();
                l2.setPrefWidth(80);
                Label l3 = new Label();
                l3.setPrefWidth(40);
                l1.setText(p.getName());
                l2.setText(String.valueOf(p.getPrice()));
                l3.setText(String.valueOf(p.getCount()));
                HBox ll = new HBox(l1, l2, l3);
                urv_scroll_v.getChildren().add(ll);
                lrv_tprice_label.setText(String.valueOf(Cart.calc_total()));
            }
        });

        urv_scroll.setContent(urv_scroll_v);

            Button lrv_pay = new Button("Pay");
            Button lrv_cancel = new Button("Cancel");

        HBox lrv_button = new HBox(lrv_pay, lrv_cancel);

        VBox lrv = new VBox(lrv_title, lrv_tprice, lrv_disc, lrv_tpay, lrv_button);
        lrv.setPrefHeight(300);

//============================================================

        // right main section
        VBox rms = new VBox(urv, lrv);
        rms.setPrefWidth(400);

        // hbox: join left and right section
        HBox root = new HBox(lms, rms);

        Scene scene = new Scene(root, 800, 550);

        return scene;

    }

    public static void main(String[] args) {
        launch();
    }

}
