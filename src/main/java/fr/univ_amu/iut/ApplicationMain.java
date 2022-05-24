package fr.univ_amu.iut;

import fr.univ_amu.iut.windows.Home;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ApplicationMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Home();
        Scene s = new Scene(root);
        primaryStage.setScene(s);
        primaryStage.show();
    }
}
