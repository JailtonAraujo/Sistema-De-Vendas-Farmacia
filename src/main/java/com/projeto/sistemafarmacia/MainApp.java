package com.projeto.sistemafarmacia;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("fxml/ViewLogin.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.getIcons().add(new Image(getClass().getResourceAsStream("Img/Icon_User_Main.png")));
        stage.setResizable(false);
        stage.setTitle("Login!");
        stage.setScene(scene);
        stage.show();
        
       
    }

    public static void main(String[] args) {
        launch();
    }
}