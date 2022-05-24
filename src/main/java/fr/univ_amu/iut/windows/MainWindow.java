package fr.univ_amu.iut.windows;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class MainWindow extends VBox {
    @FXML
    private Button buttonHelp;

    @FXML
    private Button buttonContact;

    @FXML
    private Button buttonSettings;

    public MainWindow() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/fr/univ_amu/iut/javaFX/windows/MainWindow.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @FXML
    private void initialize() {

    }

    @FXML
    public void settings() {
        System.out.println("Settings button clicked !");
        //TODO Implements settings button (pop up)
    }

    @FXML
    public void help() {
        System.out.println("Help button clicked !");
        //TODO Implements help button (tutorial)
    }

    @FXML
    public void contact() {
        System.out.println("Contact button clicked !");
        //TODO Implements contact button (pop up)
    }

}
