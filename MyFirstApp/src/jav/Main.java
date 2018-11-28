package jav;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.*;
import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../fxml/sample.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.setTitle("TopLevel Diary");
        primaryStage.setScene(new Scene(root, 700, 400));
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("file:D:/Downloads/MyFirstApp/icon2.png"));
        primaryStage.show();

    }




    public static void main(String[] args) {
        launch(args);
    }
}
