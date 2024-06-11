package hangman;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Objects;

import static hangman.DatabaseManager.check_info;
import static hangman.DatabaseManager.saving_user_info;

public class login extends Application {

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
    String  user_name ;
    String pass;



    public void log(ActionEvent e){
        name1 = name.getText();
        user_name = username.getText();
        pass = password.getText();
       // System.out.println(user_name+ pass);
        check_info(user_name,pass);

    }
    public void sign_in(){
        name1 = name.getText();
        user_name = username.getText();
        pass = password.getText();
        saving_user_info(name1,user_name,pass);


    }


    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login.fxml")));
        stage.setTitle("login");
        stage.setScene(new Scene(root));
        stage.show();
    }
    public static void main(String[] args){
        launch();
    }
}
