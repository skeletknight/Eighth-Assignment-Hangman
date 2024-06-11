package hangman;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

import static hangman.DatabaseManager.check_info;
import static hangman.DatabaseManager.saving_user_info;


public class Main extends Application {
    private Stage stage;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private TextField name;
    @FXML
    private Button login;
    @FXML
    private Button sign;
    String name1;
    String  user_name ="not real" ;
    String pass;
    public void sign_in(ActionEvent event) throws IOException {
        name1 = name.getText();
        System.out.println(username.getText());
        user_name = username.getText();
        pass = password.getText();
        saving_user_info(name1,user_name,pass);
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("hangman.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        stage.setTitle("HangMan Game");
        stage.setScene(new Scene(root));
        stage.show();



    }


    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("first.fxml")));
        stage.setTitle("login");
        stage.setScene(new Scene(root));
        stage.show();
    }


    public static void main(String[] args) throws InterruptedException {
        launch(args);

    }

    public void log(ActionEvent event) throws IOException {
        name1 = name.getText();
        user_name = username.getText();
        pass = password.getText();
        // System.out.println(user_name+ pass);
        if(check_info(user_name,pass)){
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("hangman.fxml")));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();

            stage.setTitle("HangMan Game");
            stage.setScene(new Scene(root));
            stage.show();
        }


    }
}
