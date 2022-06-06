package fr.univ_amu.iut;

import fr.univ_amu.iut.DAO.ConnectionManager;
import fr.univ_amu.iut.bundle.BundleManager;
import fr.univ_amu.iut.dialogs.ConfirmationDialog;
import fr.univ_amu.iut.dialogs.ContactDialog;
import fr.univ_amu.iut.windows.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import fr.univ_amu.iut.dialogs.EditDataDialog;
import fr.univ_amu.iut.windows.DataEntry;
import fr.univ_amu.iut.windows.Home;
import fr.univ_amu.iut.windows.Tabs;
import fr.univ_amu.iut.windows.MainWindow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.Locale;

public class ApplicationMain extends Application {
    static private final String[] AVAILABLE_LANGUAGE = {"Français", "English"};
    static public String[] getAvailableLanguage(){
        return AVAILABLE_LANGUAGE;
    }

    private Scene root;
    private VBox mainWindow;
    private Tabs tabs;

    public static final double WINDOW_WIDTH = 1200;
    public static final double WINDOW_HEIGHT = 700;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Locale.setDefault(Locale.FRENCH);

        appBasis(stage);

        ConnectionManager.getInstance();

        mainWindow = new MainWindow();
        mainWindow.getChildren().add(new Home(this));
        root = new Scene(mainWindow);
        loadCSS();

        BundleManager.setup(root);

        stage.setScene(root);
        stage.show();
    }

    @Override
    public void stop(){
        ConnectionManager.getInstance().closeConnection();

        System.exit(0);
    }

    private void appBasis(Stage stage){
        stage.setResizable(false);
        stage.setTitle("Titre de l'application");
        stage.setWidth(WINDOW_WIDTH);
        stage.setHeight(WINDOW_HEIGHT);

        tabs = Tabs.getInstance();
    }

    private void loadCSS(){
        root.getStylesheets().add("/fr/univ_amu/iut/applicationfx/Buttons.css");
        root.getStylesheets().add("/fr/univ_amu/iut/applicationfx/main.css");
        root.getStylesheets().add("/fr/univ_amu/iut/applicationfx/Tabs.css");
        root.getStylesheets().add("/fr/univ_amu/iut/applicationfx/Result.css");
        root.getStylesheets().add("/fr/univ_amu/iut/applicationfx/Error_messages.css");
        root.getStylesheets().add("/fr/univ_amu/iut/applicationfx/Bold_messages.css");
        root.getStylesheets().add("/fr/univ_amu/iut/applicationfx/ThemeButtons.css");
    }

    public void accessToData(){
        if (Home.isConnected()){
            tabs.addATab("Saisie",new DataEntry(),false,"dataEntry");
        }
        mainWindow.getChildren().set(1,tabs);

        BundleManager.updateBundle();
    }
}