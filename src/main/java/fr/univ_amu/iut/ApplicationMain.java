package fr.univ_amu.iut;

import fr.univ_amu.iut.windows.MainWindow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ApplicationMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setResizable(true);
        primaryStage.setTitle("Titre de l'application");
        Scene s = new Scene(new MainWindow());
        s.getStylesheets().add("/fr/univ_amu/iut/applicationfx/Buttons.css");
        primaryStage.setWidth(1200);
        primaryStage.setHeight(700);
        primaryStage.setScene(s);
        primaryStage.show();
    }
}
