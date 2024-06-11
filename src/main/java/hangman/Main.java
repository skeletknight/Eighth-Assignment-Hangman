package hangman;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;


public class Main extends Application {


    public void changsence(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("hangman.fxml")));
        primaryStage.setTitle("HangMan Game");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        changsence(primaryStage);


    }


    public static void main(String[] args) {
        launch(args);
    }
}
