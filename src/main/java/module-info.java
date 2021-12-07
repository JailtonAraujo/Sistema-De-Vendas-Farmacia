module com.projeto.sistemafarmacia {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.jfoenix;

    opens com.projeto.sistemafarmacia to javafx.fxml;
    exports com.projeto.sistemafarmacia;
    exports com.projeto.sistemafarmacia.controllers;
    opens com.projeto.sistemafarmacia.controllers to javafx.fxml;
}