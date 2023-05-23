module com.example.aka_cashier {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.aka_cashier to javafx.fxml;
    exports com.example.aka_cashier;
}