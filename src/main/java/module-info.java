module hangman {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.sql;
    requires java.net.http;
    requires org.json;

    opens hangman to javafx.fxml;
    exports hangman;
}