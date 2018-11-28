package jav;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

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
import javafx.scene.control.Alert;
import javafx.stage.Window;


public class SignUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button RegisterSignUpButton;

    @FXML
    private TextField name_Register_field;

    @FXML
    private TextField surname_Register_field;

    @FXML
    private TextField login_Register_field;

    @FXML
    private PasswordField password_Register_field;

    @FXML
    private Button RegisterBackButton;


    @FXML
    void initialize() throws SQLException {


        RegisterSignUpButton.setOnAction(
                event -> {
                    if (name_Register_field.getText().isEmpty() || surname_Register_field.getText().isEmpty() ||
                            login_Register_field.getText().isEmpty() || password_Register_field.getText().isEmpty())
                        animation();

                    else {
                        try {
                            getLogin();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                    alertCaller();


                });
        RegisterBackButton.setOnAction(event -> {
            RegisterBackButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/sample.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("TopLevel Diary");
            stage.getIcons().add(new Image("file:/home/anonym/IdeaProjects/MyFirstApp/icon2.png"));
            stage.setResizable(false);
            stage.show();
        });


    }


    private void animation() {

        Shake userNameFieldAnimation = new Shake(name_Register_field);
        Shake userSurnameFieldAnimation = new Shake(surname_Register_field);
        Shake userLoginAnimation = new Shake(login_Register_field);
        Shake userPasswordAnimation = new Shake(password_Register_field);
        userNameFieldAnimation.playAnimation();
        userSurnameFieldAnimation.playAnimation();
        userLoginAnimation.playAnimation();
        userPasswordAnimation.playAnimation();
    }


    private void signUpNewUser() {
        DataBaseHandler dbHandler = new DataBaseHandler();

        String firstName = name_Register_field.getText();
        String lastName = surname_Register_field.getText();
        String login = login_Register_field.getText();
        String password = password_Register_field.getText();

        User user = new User(firstName, lastName, login, password);

        dbHandler.signUpUser(user);

    }

    @FXML
    private void alertCaller() {
        Window owner = RegisterSignUpButton.getScene().getWindow();
        if (name_Register_field.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter your name");
            return;
        }
        if (surname_Register_field.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter your surname");
            return;
        }
        if (login_Register_field.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter a login");
            return;
        }
        if (password_Register_field.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter a password");
            return;
        }


    }

    private void getLogin() throws SQLException {
        DataBaseHandler dataBaseHandler = new DataBaseHandler();
        Window owner = RegisterSignUpButton.getScene().getWindow();
        User user = new User();
        user.setLogin(login_Register_field.getText());
        ResultSet resultSet = dataBaseHandler.getLogin(user);

        int counter = 0;

        while (resultSet.next()) {
            counter++;
        }
        if (counter >= 1) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "This login is already engaged");
        }
        else {
            signUpNewUser();
            AlertHelper.showAlert(Alert.AlertType.INFORMATION, owner, "Registration Successful!",
                    "Welcome " + name_Register_field.getText() + " " + surname_Register_field.getText());
        }

    }
}

