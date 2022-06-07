package fr.univ_amu.iut;

import fr.univ_amu.iut.DAO.ConnectionManager;
import fr.univ_amu.iut.bundle.BundleManager;
import javafx.scene.Node;
import javafx.scene.image.Image;
import fr.univ_amu.iut.windows.DataEntry;
import fr.univ_amu.iut.windows.Home;
import fr.univ_amu.iut.windows.Tabs;
import fr.univ_amu.iut.windows.MainWindow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Locale;

public class ApplicationMain extends Application {
    static private final String[] AVAILABLE_LANGUAGE = {"Français", "English"};
    static public String[] getAvailableLanguage(){
        return AVAILABLE_LANGUAGE;
    }

    private Scene root;
    private VBox mainWindow;
    private Tabs tabs;
    private StackPane mainStackPane;
    static private ApplicationMain instance;

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
        mainStackPane = new StackPane(mainWindow);
        mainWindow.getChildren().add(new Home(this));
        root = new Scene(mainStackPane);
        loadCSS();
        BundleManager.setup(root);
        instance = this;

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

    public void addHelp(Node node){
        this.mainStackPane.getChildren().add(node);
        this.mainStackPane.getScene().setOnMouseClicked(mouseEvent -> {
            this.mainStackPane.getChildren().remove(node);
        });
    }

    public static ApplicationMain getInstance(){
        return instance;
    }
}