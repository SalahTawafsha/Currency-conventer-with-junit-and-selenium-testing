package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader =
                new FXMLLoader(new File("src/main/java/org/example/view/Main-veiw.fxml").toURI().toURL());

        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Currency Converter");
        stage.setScene(scene);
        stage.show();
    }
}