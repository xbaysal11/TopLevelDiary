package jav;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import animations.Shake;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField login_field;

    @FXML
    private PasswordField password_field;

    @FXML
    private Button LoginLogInButton;

    @FXML
    private Button LoginSignUpButton;

    public static String getLogin;

    @FXML
    void initialize() {

        LoginLogInButton.setOnAction(event -> {
            String loginText = login_field.getText().trim();
            String passwordText = password_field.getText().trim();

            if(!loginText.equals("") && !passwordText.equals("")) {
                try {
                    loginUser(loginText, passwordText);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            else{
                Shake userLoginAnimation = new Shake(login_field);
                Shake userPasswordAnimation = new Shake(password_field);
                userLoginAnimation.playAnimation();
                userPasswordAnimation.playAnimation();
            }

        });

        LoginSignUpButton.setOnAction((ActionEvent event) -> {
            LoginSignUpButton.getScene().getWindow().hide();
                openNewWindow("/fxml/SignUp.fxml");
        });
    }
    private void loginUser(String loginText, String passwordText) throws SQLException {
        DataBaseHandler dataBaseHandler = new DataBaseHandler();
        User user = new User();
        user.setLogin(loginText);
        user.setPassword(passwordText);
        ResultSet resultSet = dataBaseHandler.getUser(user);

        int counter = 0;

        while(resultSet.next()){
            counter++;
            getLogin = login_field.getText();
        }
        if (counter >= 1 ){
            LoginLogInButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/Diary.fxml"));
            try {
                loader.load();
            }catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setResizable(true);
            stage.getIcons().add(new Image("file:/home/anonym/IdeaProjects/MyFirstApp/icon2.png"));
            stage.showAndWait();


        }else {
            Shake userLoginAnimation = new Shake(login_field);
            Shake userPasswordAnimation = new Shake(password_field);
            userLoginAnimation.playAnimation();
            userPasswordAnimation.playAnimation();
        }
    }

    public void openNewWindow(String window){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(window));
        try {
            loader.load();
        }catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.getIcons().add(new Image("file:/home/anonym/IdeaProjects/MyFirstApp/icon2.png"));
        stage.show();
    }


}
