package jav;

import java.io.*;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import javax.swing.*;

public class DiaryController extends Controller {
    @FXML private ResourceBundle resources;
    @FXML private URL location;
    @FXML private MenuBar MenuBar;
    @FXML private Menu file_menu;
    @FXML private MenuItem new_menuitem;
    @FXML private MenuItem open_menuitem;
    @FXML private MenuItem save_menuitem;
    @FXML private MenuItem exit_menuitem;
    @FXML private Menu edit_menu;
    @FXML private MenuItem copy_menuitem;
    @FXML private MenuItem paste_menuitem;
    @FXML private MenuItem cut_menuitem;
    @FXML private MenuItem clear_menuitem;
    @FXML private Menu help_menu;
    @FXML private MenuItem about_menuitem;
    @FXML private DatePicker MenuDate;
    @FXML private TextArea text_area;
    @FXML private MenuButton font_size;
    @FXML private MenuItem px12;
    @FXML private MenuItem px15;
    @FXML private MenuItem px20;
    @FXML private MenuItem px30;
    @FXML private MenuItem px45;
    @FXML private MenuItem px_custom;
    @FXML private MenuButton font_color;
    @FXML private MenuItem black;
    @FXML private MenuItem red;
    @FXML private MenuItem blue;
    @FXML private MenuItem green;
    @FXML private MenuButton font_style;
    @FXML private MenuItem normal;
    @FXML private MenuItem bold;
    @FXML private MenuItem italic;
    @FXML private MenuButton font_family;
    @FXML private MenuItem arial;
    @FXML private MenuItem times;
    @FXML private MenuItem calibri;
    @FXML private MenuItem segeo;
    @FXML private Button save;
    @FXML private Button search;

    @FXML
    private Button logOut;

    int fontSize = 20;
    public  int getFontSize(){return fontSize;}
    public void setFontSize(int fsize){fontSize =fsize;}
    String font = "Arial";
    public  String getFont(){return font;}
    public void setFont(String ffont){font =ffont;}

    Controller controller = new Controller();

    @FXML
    void initialize() {
        new_menuitem.setOnAction(event -> {
            text_area.setText("");
            try{
                Stage stage = new Stage();
                FileChooser chooser = new FileChooser();
                chooser.setTitle("New File");
                chooser.setInitialFileName(MenuDate.getValue() +".txt");
                File selectedFile = chooser.showSaveDialog(stage);
                FileWriter fileWriter = new FileWriter(selectedFile.getAbsolutePath());
                fileWriter.write(text_area.getText().toString());
                fileWriter.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        open_menuitem.setOnAction(event -> {
            try{
                Stage stage = new Stage();
                FileChooser chooser = new FileChooser();
                chooser.setTitle("Open File");
                File selectedFile = chooser.showOpenDialog(stage);
                FileReader fileReader = new FileReader(selectedFile.getAbsolutePath().toString());
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                StringBuilder stringBuilder = new StringBuilder();
                String myText = "";
                while ((myText = bufferedReader.readLine())!= null){
                    stringBuilder.append(myText+"\n");
                }
                text_area.setText(stringBuilder.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
        save_menuitem.setOnAction (event -> {
            try{
                Stage stage = new Stage();
                FileChooser chooser = new FileChooser();
                chooser.setTitle("Save File");
                chooser.setInitialFileName(MenuDate.getValue() +".txt");
                File selectedFile = chooser.showSaveDialog(stage);
                FileWriter fileWriter = new FileWriter(selectedFile.getAbsolutePath());
                fileWriter.write(text_area.getText());
                fileWriter.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        exit_menuitem.setOnAction(event -> {
            System.exit(0);
        });
        copy_menuitem.setOnAction(event -> {
            text_area.copy();
        });
        paste_menuitem.setOnAction(event -> {
            text_area.paste();
        });
        cut_menuitem.setOnAction(event -> {
            text_area.cut();
        });
        clear_menuitem.setOnAction(e -> {
            text_area.clear();
        });
        about_menuitem.setOnAction(event -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/About.fxml"));
            try {
                loader.load();
            }catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("About TopLevel Diary");
            stage.setResizable(false);
            stage.getIcons().add(new Image("file:/home/anonym/IdeaProjects/MyFirstApp/icon2.png"));
            stage.show();
        });
        arial.setOnAction(e -> {
            setFont("Arial");
            text_area.setFont(Font.font(getFont(),getFontSize()));
        });
        times.setOnAction(e -> {
            setFont("Times New Roman");
            text_area.setFont(Font.font(getFont(),getFontSize()));
        });
        calibri.setOnAction(e -> {
            setFont("Calibri");
            text_area.setFont(Font.font(getFont(),getFontSize()));
        });
        segeo.setOnAction(e -> {
            setFont("Segeo Script");
            text_area.setFont(Font.font(getFont(),getFontSize()));
        });
        px12.setOnAction(e -> {
            setFontSize(12);
            text_area.setFont(Font.font(getFontSize()));
        });
        px15.setOnAction(e -> {
            setFontSize(15);
            text_area.setFont(Font.font(getFontSize()));
        });
        px20.setOnAction(e -> {
            setFontSize(20);
            text_area.setFont(Font.font(getFontSize()));
        });
        px30.setOnAction(e -> {
            setFontSize(30);
            text_area.setFont(Font.font(getFontSize()));
        });
        px45.setOnAction(e -> {
            setFontSize(45);
            text_area.setFont(Font.font(getFontSize()));
        });
        px_custom.setOnAction(e -> {
                int font = 20;
                try {
                    font = Integer.parseInt(JOptionPane.showInputDialog("Enter Font Value"));
                } catch (Exception ev) {
                    JOptionPane.showMessageDialog(null, "Enter A Number");
                } finally {
                    setFontSize(font);
                    text_area.setFont(Font.font(getFontSize()));
                }
            });
        black.setOnAction(e -> {
            String color = "black;";
            text_area.setStyle("-fx-text-inner-color: " + color + ";");
        });
        red.setOnAction(e -> {
            String color = "red";
            text_area.setStyle("-fx-text-inner-color: " + color + ";");
        });
        blue.setOnAction(e -> {
            String color = "blue";
            text_area.setStyle("-fx-text-inner-color: " + color + ";");
        });
        green.setOnAction(e -> {
            String color = "green";
            text_area.setStyle("-fx-text-inner-color: " + color + ";");
        });
        normal.setOnAction(e -> {
            text_area.setFont(Font.font(getFont(),getFontSize()));
        });
        bold.setOnAction(e -> {
            text_area.setFont(Font.font(getFont(),FontWeight.BOLD,getFontSize()));
        });
        italic.setOnAction(e -> {
            text_area.setFont(Font.font(getFont(), FontPosture.ITALIC,getFontSize()));
        });
        save.setOnAction(event -> {
            addTextToDatabase();
            Window owner = save.getScene().getWindow();
            AlertHelper.showAlert(Alert.AlertType.INFORMATION, owner, "Success!",
                    "Your file successfully saved!");
        });
        search.setOnAction(event -> {
            try {
                searchingFiles(controller.getLogin, MenuDate.getValue().toString());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        logOut.setOnAction(event -> {
            logOut();
        });
    }

    public void logOut() {
        logOut.getScene().getWindow().hide();
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
    }

    public void addTextToDatabase() {

        DataBaseHandler dbHandler = new DataBaseHandler();

        String login = controller.getLogin;
        String textArea = text_area.getText();
        String date = MenuDate.getValue().toString();

        Text text = new Text(login, textArea, date);

        dbHandler.insertTextFile(text);
    }

    public void searchingFiles(String loginText, String date) throws SQLException {
        DataBaseHandler dataBaseHandler = new DataBaseHandler();
        Text text = new Text();
        text.setLogin(loginText);
        text.setDate(date);
        ResultSet resultSet = dataBaseHandler.searchFileFromDataBase(text);
        int counter = 0;

        Window owner = search.getScene().getWindow();

        while (resultSet.next()) {
            counter++;
            text_area.setText(resultSet.getString("textFiles"));
        }
        if(counter == 0) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "File not found"
                    , "File you searching is not found");
            return;
        }
    }
}