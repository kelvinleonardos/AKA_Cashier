package com.example.aka_cashier;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
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
    private Scene getScene1() {

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
    private Scene getScene2() {

        /*
         * - upper left vbox
         * - lower left vbox
         * - left main section
         * - upper right vbox
         * - lower right vbox
         * - right main section
         * - hbox: join left and right section
         */

        // upper left vbox
        Text ulv_title = new Text("Products List");
        ulv_title.setFont(Font.font(20));
        StackPane ulv_title_box = new StackPane(ulv_title);
        ulv_title_box.setPrefSize(400, 40);
        ulv_title_box.setAlignment(Pos.CENTER);

        // filter section
        Text ulv_filter_text = new Text("Filter:");
        ComboBox<String> ulv_filter_box = new ComboBox<>();
        ulv_filter_box.setStyle("-fx-background-color: white;");

        ulv_filter_box
                .setItems(FXCollections.observableArrayList(MyConfig.getDatabaseCol("category", "All", true, true)));

        // scrollpane secton
        ScrollPane ulv_scroll = new ScrollPane();
        ulv_scroll.setPrefSize(350, 180);
        ulv_scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        ulv_scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        VBox usv = new VBox();

        HBox ulv_filter = new HBox(ulv_filter_text, ulv_filter_box);
        ulv_filter.setPrefSize(400, 30);
        ulv_filter.setSpacing(20);
        ulv_filter.setAlignment(Pos.CENTER_LEFT);

        VBox ulv = new VBox(ulv_title_box, ulv_filter, ulv_scroll);
        ulv.setStyle("-fx-background-color: #FCEBB6; -fx-padding: 0 10 10 10;");

        ulv.setPrefHeight(250);

        // ============================================================

        // lower left vbox
        Label llv_title = new Label("Details");
        llv_title.setFont(Font.font(20));
        StackPane llv_title_box = new StackPane(llv_title);
        llv_title_box.setPrefSize(300, 40);
        llv_title_box.setAlignment(Pos.CENTER);

        Label llv_id_text = new Label("Id:");
        llv_id_text.setPrefSize(80, 15);
        TextField llv_id_field = new TextField();
        llv_id_field.setEditable(false);

        HBox llv_id = new HBox(llv_id_text, llv_id_field);
        llv_id.setAlignment(Pos.CENTER_LEFT);
        llv_id.setPrefHeight(35);

        Label llv_name_text = new Label("Name:");
        llv_name_text.setPrefSize(80, 15);
        TextField llv_name_field = new TextField();
        llv_name_field.setEditable(false);

        HBox llv_name = new HBox(llv_name_text, llv_name_field);
        llv_name.setAlignment(Pos.CENTER_LEFT);
        llv_name.setPrefHeight(35);

        Label llv_price_text = new Label("Price:");
        llv_price_text.setPrefSize(80, 15);
        TextField llv_price_field = new TextField();
        llv_price_field.setEditable(false);

        HBox llv_price = new HBox(llv_price_text, llv_price_field);
        llv_price.setAlignment(Pos.CENTER_LEFT);
        llv_price.setPrefHeight(35);

        Label llv_amount_text = new Label("amount:");
        llv_amount_text.setPrefSize(80, 15);
        TextField llv_amount_field = new TextField();

        HBox llv_amount = new HBox(llv_amount_text, llv_amount_field);
        llv_amount.setAlignment(Pos.CENTER_LEFT);
        llv_amount.setPrefHeight(35);

        Label llv_stock_text = new Label("Stock:");
        llv_stock_text.setPrefSize(80, 15);
        TextField llv_stock_field = new TextField();
        llv_stock_field.setEditable(false);

        HBox llv_stock = new HBox(llv_stock_text, llv_stock_field);
        llv_stock.setAlignment(Pos.CENTER_LEFT);
        llv_stock.setPrefHeight(35);

        Label llv_cat_text = new Label("Category:");
        llv_cat_text.setPrefSize(80, 15);
        TextField llv_cat_field = new TextField();
        llv_cat_field.setEditable(false);

        HBox llv_cat = new HBox(llv_cat_text, llv_cat_field);
        llv_cat.setAlignment(Pos.CENTER_LEFT);
        llv_cat.setPrefHeight(35);

        Button llv_add = new Button("Add to Cart");
        llv_add.setPrefSize(120, 30);
        llv_add.setStyle("-fx-background-color: #78C0A8;");

        Button llv_reset = new Button("Reset");
        llv_reset.setPrefSize(120, 30);
        llv_reset.setStyle("-fx-background-color: #78C0A8;");

        llv_reset.setOnAction(event -> {
            llv_id_field.clear();
            llv_name_field.clear();
            llv_price_field.clear();
            llv_cat_field.clear();
            llv_stock_field.clear();
        });

        HBox llv_button = new HBox(llv_add, llv_reset);
        llv_button.setAlignment(Pos.CENTER);
        llv_button.setSpacing(20);
        llv_button.getStyleClass().add("llv_button");

        for (String i : MyConfig.getDatabaseCol("id", "All", false, false)) {

            Label lid = new Label(MyConfig.getElmbyId(i, "id"));
            lid.setPrefSize(40, 20);
            Label lname = new Label(MyConfig.getElmbyId(i, "name"));
            lname.setPrefSize(120, 20);
            Label lprice = new Label(MyConfig.getElmbyId(i, "price"));
            lprice.setPrefSize(120, 20);
            Label lstock = new Label(MyConfig.getElmbyId(i, "stock"));
            lstock.setPrefSize(40, 20);
            Label lcat = new Label(MyConfig.getElmbyId(i, "category"));
            lcat.setPrefSize(100, 20);

            HBox h = new HBox(lid, lname, lprice, lstock, lcat);

            Button b = new Button();

            b.setGraphic(h);

            b.setOnAction(event -> {
                llv_id_field.setText(MyConfig.getElmbyId(i, "id"));
                llv_name_field.setText(MyConfig.getElmbyId(i, "name"));
                llv_price_field.setText(MyConfig.getElmbyId(i, "price"));
                llv_stock_field.setText(MyConfig.getElmbyId(i, "stock"));
                llv_cat_field.setText(MyConfig.getElmbyId(i, "category"));
            });

            b.getStyleClass().add("lis_col");

            usv.getChildren().add(b);

        }
        ulv_scroll.setContent(usv);

        ulv_filter_box.setOnAction(event -> {
            usv.getChildren().clear();
            try {
                for (String i : MyConfig.getDatabaseCol("id", ulv_filter_box.getValue(), false, false)) {

                    Label lid = new Label(MyConfig.getElmbyId(i, "id"));
                    lid.setPrefSize(40, 20);
                    Label lname = new Label(MyConfig.getElmbyId(i, "name"));
                    lname.setPrefSize(120, 20);
                    Label lprice = new Label(MyConfig.getElmbyId(i, "price"));
                    lprice.setPrefSize(120, 20);
                    Label lstock = new Label(MyConfig.getElmbyId(i, "stock"));
                    lstock.setPrefSize(40, 20);
                    Label lcat = new Label(MyConfig.getElmbyId(i, "category"));
                    lcat.setPrefSize(100, 20);

                    HBox h = new HBox(lid, lname, lprice, lstock, lcat);

                    Button b = new Button();

                    b.setGraphic(h);

                    b.setOnAction(ev -> {
                        llv_id_field.setText(MyConfig.getElmbyId(i, "id"));
                        llv_name_field.setText(MyConfig.getElmbyId(i, "name"));
                        llv_price_field.setText(MyConfig.getElmbyId(i, "price"));
                        llv_stock_field.setText(MyConfig.getElmbyId(i, "stock"));
                        llv_cat_field.setText(MyConfig.getElmbyId(i, "category"));
                    });

                    b.getStyleClass().add("lis_col");

                    usv.getChildren().add(b);

                }
                ulv_scroll.setContent(usv);
            } catch (Exception e) {
                System.out.println("Filter is null");
            }
        });

        VBox llv_con = new VBox(llv_title_box, llv_id, llv_name, llv_price, llv_amount, llv_button);
        llv_con.setId("llv_con");

        Button add_con = new Button("Add");
        add_con.setPrefSize(100, 40);
        add_con.setAlignment(Pos.CENTER_LEFT);
        add_con.getStyleClass().add("side_button");
        add_con.setStyle("-fx-background-color: #53412f; -fx-text-fill: white;");

        Button det_con = new Button("Details");
        det_con.setPrefSize(100, 40);
        det_con.setAlignment(Pos.CENTER_LEFT);
        det_con.getStyleClass().add("side_button");
        det_con.setStyle("-fx-background-color: #FCEBB6; -fx-text-fill: black;");

        Button edt_con = new Button("Edit");
        edt_con.setPrefSize(100, 40);
        edt_con.setAlignment(Pos.CENTER_LEFT);
        edt_con.getStyleClass().add("side_button");
        edt_con.setStyle("-fx-background-color: #53412f; -fx-text-fill: white;");

        Button del_con = new Button("Delete");
        del_con.setPrefSize(100, 40);
        del_con.setAlignment(Pos.CENTER_LEFT);
        del_con.getStyleClass().add("side_button");
        del_con.setStyle("-fx-background-color: #53412f; -fx-text-fill: white;");

        add_con.setOnAction(event -> {

            add_con.setStyle("-fx-background-color: #FCEBB6; -fx-text-fill: black;");
            det_con.setStyle("-fx-background-color: #53412f; -fx-text-fill: white;");
            edt_con.setStyle("-fx-background-color: #53412f; -fx-text-fill: white;");
            del_con.setStyle("-fx-background-color: #53412f; -fx-text-fill: white;");

            llv_title.setText("Add Product");
            llv_con.getChildren().clear();

            llv_name_field.clear();
            llv_name_field.setEditable(true);
            llv_price_field.clear();
            llv_price_field.setEditable(true);
            llv_stock_field.clear();
            llv_stock_field.setEditable(true);
            llv_cat_field.clear();
            llv_cat_field.setEditable(true);

            llv_id_field.clear();
            llv_amount_field.clear();

            llv_add.setText("Add Product");

            llv_add.setOnAction(event1 -> {

                MyConfig.addElm(llv_name_field.getText(), Integer.parseInt(llv_price_field.getText()),
                        Integer.parseInt(llv_stock_field.getText()), llv_cat_field.getText());

                ulv_filter_box.setItems(
                        FXCollections.observableArrayList(MyConfig.getDatabaseCol("category", "All", true, true)));

                usv.getChildren().clear();

                for (String i : MyConfig.getDatabaseCol("id", "All", false, false)) {

                    Label lid = new Label(MyConfig.getElmbyId(i, "id"));
                    lid.setPrefSize(40, 20);
                    Label lname = new Label(MyConfig.getElmbyId(i, "name"));
                    lname.setPrefSize(120, 20);
                    Label lprice = new Label(MyConfig.getElmbyId(i, "price"));
                    lprice.setPrefSize(120, 20);
                    Label lstock = new Label(MyConfig.getElmbyId(i, "stock"));
                    lstock.setPrefSize(40, 20);
                    Label lcat = new Label(MyConfig.getElmbyId(i, "category"));
                    lcat.setPrefSize(100, 20);

                    HBox h = new HBox(lid, lname, lprice, lstock, lcat);

                    Button b = new Button();

                    b.setGraphic(h);

                    b.setOnAction(event4 -> {
                        llv_id_field.setText(MyConfig.getElmbyId(i, "id"));
                        llv_name_field.setText(MyConfig.getElmbyId(i, "name"));
                        llv_price_field.setText(MyConfig.getElmbyId(i, "price"));
                        llv_stock_field.setText(MyConfig.getElmbyId(i, "stock"));
                        llv_cat_field.setText(MyConfig.getElmbyId(i, "category"));
                    });

                    b.getStyleClass().add("lis_col");

                    usv.getChildren().add(b);

                }
                ulv_scroll.setContent(usv);

                ulv_filter_box.setValue("All");


                llv_id_field.clear();
                llv_name_field.clear();
                llv_price_field.clear();
                llv_amount_field.clear();
                llv_stock_field.clear();
                llv_cat_field.clear();

            });

            llv_con.getChildren().addAll(llv_title_box, llv_name, llv_price, llv_stock, llv_cat, llv_button);
        });

        edt_con.setOnAction(event5 -> {

            add_con.setStyle("-fx-background-color: #53412f; -fx-text-fill: white;");
            det_con.setStyle("-fx-background-color: #53412f; -fx-text-fill: white;");
            edt_con.setStyle("-fx-background-color: #FCEBB6; -fx-text-fill: black;");
            del_con.setStyle("-fx-background-color: #53412f; -fx-text-fill: white;");

            llv_title.setText("Edit Data");
            llv_con.getChildren().clear();

            llv_id_field.clear();
            llv_id_field.setEditable(false);

            llv_name_field.clear();
            llv_name_field.setEditable(true);
            llv_price_field.clear();
            llv_price_field.setEditable(true);
            llv_stock_field.clear();
            llv_stock_field.setEditable(true);
            llv_cat_field.clear();
            llv_cat_field.setEditable(true);

            llv_add.setText("Edit Data");

            llv_add.setOnAction(event1 -> {
                if (Cart.getP().size()==0) {
                    MyConfig.editElm(Integer.parseInt(llv_id_field.getText()), llv_name_field.getText(),
                            Integer.parseInt(llv_price_field.getText()), Integer.parseInt(llv_stock_field.getText()),
                            llv_cat_field.getText());

                    ulv_filter_box.setItems(
                            FXCollections.observableArrayList(MyConfig.getDatabaseCol("category", "All", true, true)));

                    usv.getChildren().clear();

                    for (String i : MyConfig.getDatabaseCol("id", "All", false, false)) {

                        Label lid = new Label(MyConfig.getElmbyId(i, "id"));
                        lid.setPrefSize(40, 20);
                        Label lname = new Label(MyConfig.getElmbyId(i, "name"));
                        lname.setPrefSize(120, 20);
                        Label lprice = new Label(MyConfig.getElmbyId(i, "price"));
                        lprice.setPrefSize(120, 20);
                        Label lstock = new Label(MyConfig.getElmbyId(i, "stock"));
                        lstock.setPrefSize(40, 20);
                        Label lcat = new Label(MyConfig.getElmbyId(i, "category"));
                        lcat.setPrefSize(100, 20);

                        HBox h = new HBox(lid, lname, lprice, lstock, lcat);

                        Button b = new Button();

                        b.setGraphic(h);

                        b.setOnAction(event4 -> {
                            llv_id_field.setText(MyConfig.getElmbyId(i, "id"));
                            llv_name_field.setText(MyConfig.getElmbyId(i, "name"));
                            llv_price_field.setText(MyConfig.getElmbyId(i, "price"));
                            llv_stock_field.setText(MyConfig.getElmbyId(i, "stock"));
                            llv_cat_field.setText(MyConfig.getElmbyId(i, "category"));
                        });

                        b.getStyleClass().add("lis_col");

                        usv.getChildren().add(b);

                    }
                    ulv_scroll.setContent(usv);
                } else {
                    Text tomax = new Text("To edit product details, cart must be empty!");

                    StackPane tomaxst = new StackPane(tomax);
                    tomaxst.setPrefSize(250, 100);

                    Button buok = new Button("Ok");
                    buok.setPrefSize(70, 25);

                    VBox tom = new VBox(tomaxst, buok);
                    tom.setAlignment(Pos.CENTER);

                    Scene keb = new Scene(tom, 300, 140);

                    Stage kebs = new Stage();
                    kebs.setScene(keb);
                    kebs.setTitle("AKA CASHIER");
                    kebs.setResizable(false);
                    kebs.show();

                    buok.setOnAction(event39 -> {
                        kebs.close();
                    });

                    final int[] cts = {5};

                    Timeline ctl = new Timeline();
                    ctl.setCycleCount(cts[0]);
                    ctl.getKeyFrames().add(
                        new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                cts[0]--;
                                if (cts[0] == 0) {
                                    kebs.close();
                                } else {
                                    buok.setText("Ok (" + cts[0] + ")");
                                }
                            }
                        })
                    );
                    ctl.play();
                }

                ulv_filter_box.setValue("All");

                llv_id_field.clear();
                llv_name_field.clear();
                llv_price_field.clear();
                llv_stock_field.clear();
                llv_cat_field.clear();

            });

            llv_con.getChildren().addAll(llv_title_box, llv_id, llv_name, llv_price, llv_stock, llv_cat, llv_button);
        });

        del_con.setOnAction(event6 -> {

            add_con.setStyle("-fx-background-color: #53412f; -fx-text-fill: white;");
            det_con.setStyle("-fx-background-color: #53412f; -fx-text-fill: white;");
            edt_con.setStyle("-fx-background-color: #53412f; -fx-text-fill: white;");
            del_con.setStyle("-fx-background-color: #FCEBB6; -fx-text-fill: black;");

            llv_title.setText("Delete Product");
            llv_con.getChildren().clear();

            llv_id_field.clear();
            llv_id_field.setEditable(false);

            llv_name_field.clear();
            llv_name_field.setEditable(false);
            llv_price_field.clear();
            llv_price_field.setEditable(false);
            llv_stock_field.clear();
            llv_stock_field.setEditable(false);
            llv_cat_field.clear();
            llv_cat_field.setEditable(false);

            llv_add.setText("Delete Product");

            llv_add.setOnAction(event1 -> {

                if (Cart.getP().size()==0) {
                    MyConfig.delElm(Integer.parseInt(llv_id_field.getText()));

                    ulv_filter_box.setItems(
                            FXCollections.observableArrayList(MyConfig.getDatabaseCol("category", "All", true, true)));

                    usv.getChildren().clear();

                    for (String i : MyConfig.getDatabaseCol("id", "All", false, false)) {

                        Label lid = new Label(MyConfig.getElmbyId(i, "id"));
                        lid.setPrefSize(40, 20);
                        Label lname = new Label(MyConfig.getElmbyId(i, "name"));
                        lname.setPrefSize(120, 20);
                        Label lprice = new Label(MyConfig.getElmbyId(i, "price"));
                        lprice.setPrefSize(120, 20);
                        Label lstock = new Label(MyConfig.getElmbyId(i, "stock"));
                        lstock.setPrefSize(40, 20);
                        Label lcat = new Label(MyConfig.getElmbyId(i, "category"));
                        lcat.setPrefSize(100, 20);

                        HBox h = new HBox(lid, lname, lprice, lstock, lcat);

                        Button b = new Button();

                        b.setGraphic(h);

                        b.setOnAction(event4 -> {
                            llv_id_field.setText(MyConfig.getElmbyId(i, "id"));
                            llv_name_field.setText(MyConfig.getElmbyId(i, "name"));
                            llv_price_field.setText(MyConfig.getElmbyId(i, "price"));
                            llv_stock_field.setText(MyConfig.getElmbyId(i, "stock"));
                            llv_cat_field.setText(MyConfig.getElmbyId(i, "category"));
                        });

                        b.getStyleClass().add("lis_col");

                        usv.getChildren().add(b);

                    }
                    ulv_scroll.setContent(usv);
                } else {
                    Text tomax = new Text("To delete product details, cart must be empty!");

                    StackPane tomaxst = new StackPane(tomax);
                    tomaxst.setPrefSize(250, 100);

                    Button buok = new Button("Ok");
                    buok.setPrefSize(70, 25);

                    VBox tom = new VBox(tomaxst, buok);
                    tom.setAlignment(Pos.CENTER);

                    Scene keb = new Scene(tom, 300, 140);

                    Stage kebs = new Stage();
                    kebs.setScene(keb);
                    kebs.setTitle("AKA CASHIER");
                    kebs.setResizable(false);
                    kebs.show();

                    buok.setOnAction(event39 -> {
                        kebs.close();
                    });

                    final int[] cts = {5};

                    Timeline ctl = new Timeline();
                    ctl.setCycleCount(cts[0]);
                    ctl.getKeyFrames().add(
                        new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                cts[0]--;
                                if (cts[0] == 0) {
                                    kebs.close();
                                } else {
                                    buok.setText("Ok (" + cts[0] + ")");
                                }
                            }
                        })
                    );
                    ctl.play();
                }

                ulv_filter_box.setValue("All");

                llv_id_field.clear();
                llv_name_field.clear();
                llv_price_field.clear();
                llv_stock_field.clear();
                llv_cat_field.clear();

            });

            llv_con.getChildren().addAll(llv_title_box, llv_id, llv_name, llv_price, llv_stock, llv_cat, llv_button);
        });

        VBox llv_sb = new VBox(add_con, det_con, edt_con, del_con);
        llv_sb.setPrefSize(100, 300);
        llv_sb.setAlignment(Pos.CENTER);
        llv_sb.setId("side_bar");

        HBox llv = new HBox(llv_sb, llv_con);
        llv.setPrefHeight(300);

        // ============================================================

        // left main section
        VBox lms = new VBox(ulv, llv);
        lms.setPrefWidth(400);

        // ============================================================

        // upper right vbox
        Text urv_title = new Text("Buying List");
        urv_title.setFont(Font.font(20));
        StackPane urv_title_box = new StackPane(urv_title);
        urv_title_box.setPrefSize(400, 40);
        urv_title_box.setAlignment(Pos.CENTER);

        ScrollPane urv_scroll = new ScrollPane();
        urv_scroll.setPrefSize(350, 210);
        urv_scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        urv_scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        VBox urv_scroll_v = new VBox();

        VBox urv = new VBox(urv_title_box, urv_scroll);
        urv.setStyle("-fx-background-color: #FCEBB6; -fx-padding: 0 10 10 10;");
        urv.setPrefHeight(250);

        // ============================================================

        // lower right vbox
        Text lrv_title = new Text("Transaction");
        lrv_title.setFont(Font.font(20));
        StackPane lrv_title_box = new StackPane(lrv_title);
        lrv_title_box.setPrefSize(400, 40);
        lrv_title_box.setAlignment(Pos.CENTER);

        Label lrv_tprice_text = new Label("Total Price:");
        lrv_tprice_text.setPrefSize(100, 15);
        Label lrv_tprice_label = new Label();
        lrv_tprice_label.setText("0");

        HBox lrv_tprice = new HBox(lrv_tprice_text, lrv_tprice_label);
        lrv_tprice.setAlignment(Pos.CENTER_LEFT);
        lrv_tprice.setPrefHeight(35);

        Label lrv_disc_text = new Label("Discount:");
        lrv_disc_text.setPrefSize(100, 15);
        TextField lrv_disc_label = new TextField();

        lrv_disc_label.setPrefWidth(50);
        Label percent = new Label("%");

        HBox lrv_disc = new HBox(lrv_disc_text, lrv_disc_label, percent);
        lrv_disc.setAlignment(Pos.CENTER_LEFT);
        lrv_disc.setPrefHeight(35);

        Label lrv_tpay_text = new Label("Total Pay:");
        lrv_tpay_text.setPrefSize(100, 15);
        Label lrv_tpay_label = new Label();
        lrv_tpay_label.setText("0");

        lrv_disc_label.setOnAction(event8 -> {
            double sblm = Double.parseDouble(lrv_tprice_label.getText());
            double disc = Double.parseDouble(lrv_disc_label.getText());
            double ssdh = sblm-((sblm*disc)/100);
            lrv_tpay_label.setText(String.valueOf(ssdh));
        });

        HBox lrv_tpay = new HBox(lrv_tpay_text, lrv_tpay_label);
        lrv_tpay.setAlignment(Pos.CENTER_LEFT);
        lrv_tpay.setPrefHeight(35);

        llv_add.setOnAction(event -> {
            try {
                if (Cart.getPn().contains(llv_name_field.getText())) {
                    if (Integer.parseInt(MyConfig.getElmbyId(llv_id_field.getText(), "stock" ))>=Integer.parseInt(llv_amount_field.getText())+Cart.getP().get(Cart.getPn().indexOf(llv_name_field.getText())).getCount()) {
                        urv_scroll_v.getChildren().clear();
                        Cart.add_product(Integer.parseInt(llv_id_field.getText()), llv_name_field.getText(),
                                Integer.parseInt(llv_price_field.getText()), llv_cat_field.getText(), Integer.parseInt(llv_amount_field.getText()));
                        for (Product p : Cart.getP()) {
                            Label l1 = new Label();
                            l1.setPrefSize(100, 20);
                            Label l2 = new Label();
                            l2.setPrefSize(80, 20);
                            Label l3 = new Label();
                            l3.setPrefSize(40, 20);
                            l1.setText(p.getName());
                            l2.setText(String.valueOf(p.getPrice()));
                            l3.setText(String.valueOf(p.getCount()));
                            HBox ll = new HBox(l1, l2, l3);
                            urv_scroll_v.getChildren().add(ll);
                            lrv_tprice_label.setText(String.valueOf(Cart.calc_total()));
                            lrv_tpay_label.setText(String.valueOf(Cart.calc_total()));
                        }
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

                        final int[] cts = {5};

                        Timeline ctl = new Timeline();
                        ctl.setCycleCount(cts[0]);
                        ctl.getKeyFrames().add(
                            new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    cts[0]--;
                                    if (cts[0] == 0) {
                                        kebs.close();
                                    } else {
                                        buok.setText("Ok (" + cts[0] + ")");
                                    }
                                }
                            })
                        );
                        ctl.play();

                    }
                } else {
                    if (Integer.parseInt(MyConfig.getElmbyId(llv_id_field.getText(), "stock" ))>=Integer.parseInt(llv_amount_field.getText())) {
                        urv_scroll_v.getChildren().clear();
                        Cart.add_product(Integer.parseInt(llv_id_field.getText()), llv_name_field.getText(),
                                Integer.parseInt(llv_price_field.getText()), llv_cat_field.getText(), Integer.parseInt(llv_amount_field.getText()));
                        for (Product p : Cart.getP()) {
                            Label l1 = new Label();
                            l1.setPrefSize(100, 20);
                            Label l2 = new Label();
                            l2.setPrefSize(80, 20);
                            Label l3 = new Label();
                            l3.setPrefSize(40, 20);
                            l1.setText(p.getName());
                            l2.setText(String.valueOf(p.getPrice()));
                            l3.setText(String.valueOf(p.getCount()));
                            HBox ll = new HBox(l1, l2, l3);
                            urv_scroll_v.getChildren().add(ll);
                            lrv_tprice_label.setText(String.valueOf(Cart.calc_total()));
                            lrv_tpay_label.setText(String.valueOf(Cart.calc_total()));
                        }
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

                        final int[] cts = {5};

                        Timeline ctl = new Timeline();
                        ctl.setCycleCount(cts[0]);
                        ctl.getKeyFrames().add(
                            new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    cts[0]--;
                                    if (cts[0] == 0) {
                                        kebs.close();
                                    } else {
                                        buok.setText("Ok (" + cts[0] + ")");
                                    }
                                }
                            })
                        );
                        ctl.play();

                    }
                }
            } catch (Exception e) {
                Text amount_warn = new Text("Please Insert Product Amount!");

                StackPane saw = new StackPane(amount_warn);
                saw.setPrefSize(200, 100);

                Button bok = new Button("Ok");
                bok.setPrefSize(70, 25);

                VBox not = new VBox(saw, bok);
                not.setAlignment(Pos.CENTER);

                Scene sawnot = new Scene(not, 250, 140);

                Stage sawt = new Stage();
                sawt.setScene(sawnot);
                sawt.setTitle("AKA CASHIER");
                sawt.setResizable(false);
                sawt.show();

                bok.setOnAction(event39 -> {
                    sawt.close();
                });

                final int[] cts = {5};

                Timeline ctl = new Timeline();
                ctl.setCycleCount(cts[0]);
                ctl.getKeyFrames().add(
                    new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            cts[0]--;
                            if (cts[0] == 0) {
                                sawt.close();
                            } else {
                                bok.setText("Ok (" + cts[0] + ")");
                            }
                        }
                    })
                );
                ctl.play();

            }
            llv_id_field.clear();
            llv_name_field.clear();
            llv_price_field.clear();
            llv_amount_field.clear();
            llv_cat_field.clear();
        });

        det_con.setOnAction(event -> {

            add_con.setStyle("-fx-background-color: #53412f; -fx-text-fill: white;");
            det_con.setStyle("-fx-background-color: #FCEBB6; -fx-text-fill: black;");
            edt_con.setStyle("-fx-background-color: #53412f; -fx-text-fill: white;");
            del_con.setStyle("-fx-background-color: #53412f; -fx-text-fill: white;");

            llv_title.setText("Details");
            llv_con.getChildren().clear();

            llv_id_field.clear();
            llv_name_field.clear();
            llv_price_field.clear();
            llv_amount_field.clear();

            llv_id_field.setEditable(false);
            llv_name_field.setEditable(false);
            llv_price_field.setEditable(false);
            llv_amount_field.setEditable(true);

            llv_add.setText("Add to Cart");

            llv_add.setOnAction(event23 -> {
                try {
                    if (Cart.getPn().contains(llv_name_field.getText())) {
                        if (Integer.parseInt(MyConfig.getElmbyId(llv_id_field.getText(), "stock" ))>=Integer.parseInt(llv_amount_field.getText())+Cart.getP().get(Cart.getPn().indexOf(llv_name_field.getText())).getCount()) {
                            urv_scroll_v.getChildren().clear();
                            Cart.add_product(Integer.parseInt(llv_id_field.getText()), llv_name_field.getText(),
                                    Integer.parseInt(llv_price_field.getText()), llv_cat_field.getText(), Integer.parseInt(llv_amount_field.getText()));
                            for (Product p : Cart.getP()) {
                                Label l1 = new Label();
                                l1.setPrefSize(100, 20);
                                Label l2 = new Label();
                                l2.setPrefSize(80, 20);
                                Label l3 = new Label();
                                l3.setPrefSize(40, 20);
                                l1.setText(p.getName());
                                l2.setText(String.valueOf(p.getPrice()));
                                l3.setText(String.valueOf(p.getCount()));
                                HBox ll = new HBox(l1, l2, l3);
                                urv_scroll_v.getChildren().add(ll);
                                lrv_tprice_label.setText(String.valueOf(Cart.calc_total()));
                                lrv_tpay_label.setText(String.valueOf(Cart.calc_total()));
                            }
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

                            final int[] cts = {5};

                            Timeline ctl = new Timeline();
                            ctl.setCycleCount(cts[0]);
                            ctl.getKeyFrames().add(
                                new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle(ActionEvent event) {
                                        cts[0]--;
                                        if (cts[0] == 0) {
                                            kebs.close();
                                        } else {
                                            buok.setText("Ok (" + cts[0] + ")");
                                        }
                                    }
                                })
                            );
                            ctl.play();

                        }
                    } else {
			            if (Integer.parseInt(MyConfig.getElmbyId(llv_id_field.getText(), "stock" ))>=Integer.parseInt(llv_amount_field.getText())) {
                            urv_scroll_v.getChildren().clear();
                            Cart.add_product(Integer.parseInt(llv_id_field.getText()), llv_name_field.getText(),
                                    Integer.parseInt(llv_price_field.getText()), llv_cat_field.getText(), Integer.parseInt(llv_amount_field.getText()));
                            for (Product p : Cart.getP()) {
                                Label l1 = new Label();
                                l1.setPrefSize(100, 20);
                                Label l2 = new Label();
                                l2.setPrefSize(80, 20);
                                Label l3 = new Label();
                                l3.setPrefSize(40, 20);
                                l1.setText(p.getName());
                                l2.setText(String.valueOf(p.getPrice()));
                                l3.setText(String.valueOf(p.getCount()));
                                HBox ll = new HBox(l1, l2, l3);
                                urv_scroll_v.getChildren().add(ll);
                                lrv_tprice_label.setText(String.valueOf(Cart.calc_total()));
                                lrv_tpay_label.setText(String.valueOf(Cart.calc_total()));
                            }
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

                            final int[] cts = {5};

                            Timeline ctl = new Timeline();
                            ctl.setCycleCount(cts[0]);
                            ctl.getKeyFrames().add(
                                new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle(ActionEvent event) {
                                        cts[0]--;
                                        if (cts[0] == 0) {
                                            kebs.close();
                                        } else {
                                            buok.setText("Ok (" + cts[0] + ")");
                                        }
                                    }
                                })
                            );
                            ctl.play();

                        }
                    }
                } catch (Exception e) {
                    Text amount_warn = new Text("Please Insert Product Amount!");

                    StackPane saw = new StackPane(amount_warn);
                    saw.setPrefSize(200, 100);

                    Button bok = new Button("Ok");
                    bok.setPrefSize(70, 25);

                    VBox not = new VBox(saw, bok);
                    not.setAlignment(Pos.CENTER);

                    Scene sawnot = new Scene(not, 250, 140);

                    Stage sawt = new Stage();
                    sawt.setScene(sawnot);
                    sawt.setTitle("AKA CASHIER");
                    sawt.setResizable(false);
                    sawt.show();

                    bok.setOnAction(event39 -> {
                        sawt.close();
                    });

                    final int[] cts = {5};

                    Timeline ctl = new Timeline();
                    ctl.setCycleCount(cts[0]);
                    ctl.getKeyFrames().add(
                        new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                cts[0]--;
                                if (cts[0] == 0) {
                                    sawt.close();
                                } else {
                                    bok.setText("Ok (" + cts[0] + ")");
                                }
                            }
                        })
                    );
                    ctl.play();

                }
                llv_id_field.clear();
                llv_name_field.clear();
                llv_price_field.clear();
                llv_amount_field.clear();
                llv_cat_field.clear();
            });

            llv_con.getChildren().addAll(llv_title_box, llv_id, llv_name, llv_price, llv_amount, llv_button);
        });

        urv_scroll.setContent(urv_scroll_v);

        Button lrv_pay = new Button("Pay");
        lrv_pay.setPrefSize(120, 30);
        lrv_pay.setStyle("-fx-background-color: #78C0A8;");

        lrv_pay.setOnAction(event9 -> {

            if (!lrv_tpay_label.getText().equals("0")) {
                for (Product f : Cart.getP()) {
                    int fj = Integer.parseInt(MyConfig.getElmbyId(String.valueOf(f.getId()), "stock"));
                    MyConfig.editElm(f.getId(), f.getName(), f.getPrice(), Integer.parseInt(MyConfig.getElmbyId(String.valueOf(f.getId()), "stock")) - f.getCount(), MyConfig.getElmbyId(String.valueOf(f.getId()), "category"));
                    if (f.getCount() == fj) {
                        MyConfig.delElm(f.getId());
                    }
                }

                ulv_filter_box.setItems(
                        FXCollections.observableArrayList(MyConfig.getDatabaseCol("category", "All", true, true)));

                usv.getChildren().clear();

                for (String i : MyConfig.getDatabaseCol("id", "All", false, false)) {

                    Label lid = new Label(MyConfig.getElmbyId(i, "id"));
                    lid.setPrefSize(40, 20);
                    Label lname = new Label(MyConfig.getElmbyId(i, "name"));
                    lname.setPrefSize(120, 20);
                    Label lprice = new Label(MyConfig.getElmbyId(i, "price"));
                    lprice.setPrefSize(120, 20);
                    Label lstock = new Label(MyConfig.getElmbyId(i, "stock"));
                    lstock.setPrefSize(40, 20);
                    Label lcat = new Label(MyConfig.getElmbyId(i, "category"));
                    lcat.setPrefSize(100, 20);

                    HBox h = new HBox(lid, lname, lprice, lstock, lcat);

                    Button b = new Button();

                    b.setGraphic(h);

                    b.setOnAction(event4 -> {
                        llv_id_field.setText(MyConfig.getElmbyId(i, "id"));
                        llv_name_field.setText(MyConfig.getElmbyId(i, "name"));
                        llv_price_field.setText(MyConfig.getElmbyId(i, "price"));
                        llv_stock_field.setText(MyConfig.getElmbyId(i, "stock"));
                        llv_cat_field.setText(MyConfig.getElmbyId(i, "category"));
                    });

                    b.getStyleClass().add("lis_col");

                    usv.getChildren().add(b);

                }
                ulv_scroll.setContent(usv);

                ulv_filter_box.setValue("All");

                MyConfig.addElm(Double.parseDouble(lrv_tpay_label.getText()));

                Cart.clear_cart();

                urv_scroll_v.getChildren().clear();
                lrv_tprice_label.setText("0");
                lrv_disc_label.clear();
                lrv_tpay_label.setText("0");

                llv_id_field.clear();
                llv_name_field.clear();
                llv_price_field.clear();
                llv_amount_field.clear();
                llv_cat_field.clear();

                Text suc_text = new Text("Payment Success");

                StackPane suc = new StackPane(suc_text);
                suc.setPrefSize(200, 100);

                Button ok = new Button("Ok");
                ok.setPrefSize(70, 25);

                VBox not = new VBox(suc, ok);
                not.setAlignment(Pos.CENTER);

                Scene notif = new Scene(not, 250, 140);

                Stage snotif = new Stage();
                snotif.setScene(notif);
                snotif.setTitle("AKA CASHIER");
                snotif.setResizable(false);
                snotif.show();

                ok.setOnAction(event10 -> {
                    snotif.close();
                });

                final int[] cts = {5};

                Timeline ctl = new Timeline();
                ctl.setCycleCount(cts[0]);
                ctl.getKeyFrames().add(
                    new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            cts[0]--;
                            if (cts[0] == 0) {
                                snotif.close();
                            } else {
                                ok.setText("Ok (" + cts[0] + ")");
                            }
                        }
                    })
                );
                ctl.play();
            } else {
                Text suc_text = new Text("Please add product to cart!");

                StackPane suc = new StackPane(suc_text);
                suc.setPrefSize(250, 100);

                Button ok = new Button("Ok");
                ok.setPrefSize(70, 25);

                VBox not = new VBox(suc, ok);
                not.setAlignment(Pos.CENTER);

                Scene notif = new Scene(not, 300, 140);

                Stage snotif = new Stage();
                snotif.setScene(notif);
                snotif.setTitle("AKA CASHIER");
                snotif.setResizable(false);
                snotif.show();

                ok.setOnAction(event10 -> {
                    snotif.close();
                });

                final int[] cts = {5};

                Timeline ctl = new Timeline();
                ctl.setCycleCount(cts[0]);
                ctl.getKeyFrames().add(
                    new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            cts[0]--;
                            if (cts[0] == 0) {
                                snotif.close();
                            } else {
                                ok.setText("Ok (" + cts[0] + ")");
                            }
                        }
                    })
                );
                ctl.play();
            }
        });

        Button lrv_cancel = new Button("Cancel");
        lrv_cancel.setPrefSize(120, 30);
        lrv_cancel.setStyle("-fx-background-color: #78C0A8;");

        lrv_cancel.setOnAction(event19 -> {
            Cart.clear_cart();

            urv_scroll_v.getChildren().clear();
            lrv_tprice_label.setText("0");
            lrv_disc_label.clear();
            lrv_tpay_label.setText("0");
        });

        Button lrv_history = new Button("History");
        lrv_history.setPrefSize(120, 30);
        lrv_history.setStyle("-fx-background-color: #78C0A8;");

        lrv_history.setOnAction(event19 -> {

            VBox anu = new VBox();

            for (int q=0; q<MyConfig.getDatabaseCol("time").size(); q++) {
                Label tim = new Label();
                tim.setPrefSize(250, 30);
                Label pri = new Label();
                pri.setPrefSize(100, 30);
                tim.setText(MyConfig.getDatabaseCol("time").get(q));
                pri.setText(MyConfig.getDatabaseCol("price").get(q));
                HBox hpr = new HBox(tim, pri);
                anu.getChildren().add(hpr);
            }

            ScrollPane sch = new ScrollPane(anu);
            anu.setPrefSize(400, 400);

            Scene anuan = new Scene(sch);

            Stage anuscene = new Stage();
            anuscene.setScene(anuan);
            anuscene.setTitle("AKA CASHIER");
            anuscene.setResizable(false);
            anuscene.show();

        });

        HBox lrv_button = new HBox(lrv_pay, lrv_cancel, lrv_history);
        lrv_button.setAlignment(Pos.CENTER);
        lrv_button.setSpacing(20);

        VBox lrv = new VBox(lrv_title_box, lrv_tprice, lrv_disc, lrv_tpay, lrv_button);
        lrv.setStyle("-fx-background-color: #FCEBB6;");
        lrv.setPrefHeight(300);

        // ============================================================

        // right main section
        VBox rms = new VBox(urv, lrv);
        rms.setPrefWidth(400);

        // ============================================================

        // hbox: join left and right section
        HBox root = new HBox(lms, rms);

        Scene scene = new Scene(root, 800, 550);
        scene.getStylesheets().add(getClass().getResource("/styles/styles.css").toExternalForm());


        return scene;

    }

    public static void main(String[] args) {
        launch();
    }

}
