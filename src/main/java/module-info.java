module com.projeto.sistemafarmacia {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.projeto.sistemafarmacia to javafx.fxml;
    exports com.projeto.sistemafarmacia;
}