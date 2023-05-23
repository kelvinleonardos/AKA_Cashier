package com.example.aka_cashier;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
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
        opening.setPrefSize(360, 240);

        Scene scene = new Scene(opening);

        PauseTransition delay = new PauseTransition(Duration.seconds(1));
        delay.setOnFinished(event -> {
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
            ComboBox ulv_filter_box = new ComboBox();

            // scrollpane secton
            ScrollPane ulv_scroll = new ScrollPane();

        HBox ulv_filter = new HBox(ulv_filter_text, ulv_filter_box);

        VBox ulv = new VBox(ulv_title, ulv_filter, ulv_scroll);

//============================================================

        // lower left vbox
        Text llv_title = new Text("Details");

            Text llv_id_text = new Text("Id:");
            TextField llv_id_field = new TextField();

        HBox llv_id = new HBox(llv_id_text, llv_id_field);

            Text llv_name_text = new Text("Name:");
            TextField llv_name_field = new TextField();

        HBox llv_name = new HBox(llv_name_text, llv_name_field);

            Text llv_price_text = new Text("Price:");
            TextField llv_price_field = new TextField();

        HBox llv_price = new HBox(llv_price_text, llv_price_field);

            Button llv_add = new Button("Add to Cart");
            Button llv_reset = new Button("Reset");

        HBox llv_button = new HBox(llv_add, llv_reset);

        VBox llv = new VBox(llv_title, llv_id, llv_name, llv_price, llv_button);

//============================================================

        // left main section
        VBox lms = new VBox(ulv, llv);

//============================================================

        // upper right vbox
        Text urv_title = new Text("Buying List");

        ScrollPane urv_scroll = new ScrollPane();

        VBox urv = new VBox(urv_title, urv_scroll);

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

            Button lrv_pay = new Button("Pay");
            Button lrv_cancel = new Button("Cancel");

        HBox lrv_button = new HBox(lrv_pay, lrv_cancel);

        VBox lrv = new VBox(lrv_title, lrv_tprice, lrv_disc, lrv_tpay, lrv_button);

//============================================================

        // right main section
        VBox rms = new VBox(urv, lrv);

        // hbox: join left and right section
        HBox root = new HBox(lms, rms);

        Scene scene = new Scene(root, 800, 550);

        return scene;

    }

    public static void main(String[] args) {
        launch();
    }

}
