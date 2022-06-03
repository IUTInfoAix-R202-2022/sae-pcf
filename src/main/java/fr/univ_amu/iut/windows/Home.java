package fr.univ_amu.iut.windows;

import fr.univ_amu.iut.ApplicationMain;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class Home extends Pane {
    private static boolean connected = false;
    private ApplicationMain application;

    public static boolean isConnected() {
        return connected;
    }

    public Home(ApplicationMain application) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/fr/univ_amu/iut/javaFX/windows/Home.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.application = application;
    }

    @FXML
    private TextField userNameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button submitButton;

    @FXML
    private Label errorMessage;

    @FXML
    void submit(){
        if (userNameField.getText().equals("admin") && passwordField.getText().equals("admin")){
            connected = true;
            application.accessToData();
        }
        else {
            errorMessage.setVisible(true);
        }
    }

    @FXML
    void accessToData(){
        application.accessToData();
    }
}
