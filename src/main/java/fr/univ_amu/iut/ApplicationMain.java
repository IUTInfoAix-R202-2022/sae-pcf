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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Locale;

public class ApplicationMain extends Application {
    static private final String[] AVAILABLE_LANGUAGE = {"Français", "English"};
    static public String[] getAvailableLanguage(){
        return AVAILABLE_LANGUAGE;
    }

    public static ApplicationMain getInstance(){
        return instance;
    }

    public static javafx.collections.ObservableList<String> getStyleSheets() {
        return root.getStylesheets();
    }
    private static Scene root;
    private VBox mainWindow;
    private Tabs tabs;
    private StackPane mainStackPane;
    static private ApplicationMain instance;

    public static final double WINDOW_WIDTH = 1200;
    public static final double WINDOW_HEIGHT = 700;
    public static boolean DARKTHEMEENABLED = false;

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
        ConnectionManager.getInstance().closeConnection(); // We correctly close the database connection on stop

        System.exit(0); // for correctly end the program
    }

    private void appBasis(Stage stage){
        stage.setResizable(false);   // We decided to make a not responsive window
        stage.setTitle("Cartographie des usages numériques");
        stage.getIcons().add(new Image("fr/univ_amu/iut/applicationfx/icon.png"));
        stage.setWidth(WINDOW_WIDTH);
        stage.setHeight(WINDOW_HEIGHT);

        tabs = Tabs.getInstance();   // The TabPane is an instance because the TabPane is always displayed after log in
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

    public static void unloadDarkCSS(){
        root.getStylesheets().remove(root.getStylesheets().size()-1);
    }

    public static void loadACSSFile(String url) {
        root.getStylesheets().add(url);
    }

    public void accessToData(){
        if (Home.isConnected()){
            tabs.addATab("Saisie",new DataEntry(),false,"dataEntry");  // Add the dataEntry tab only for the admin
        }
        mainWindow.getChildren().set(1,tabs);

        BundleManager.updateBundle();  // Loading the language bundle
    }

    public void addHelp(Node node){   // method for display a node to the screen, if this node is clicked, it disappears
        this.mainStackPane.getChildren().add(node);  // add the node to the main StackPane it will display the node at front in the scene
        this.mainStackPane.getScene().setOnMouseClicked(mouseEvent -> {  // add an EventHandler for this node when clicked
            this.mainStackPane.getChildren().remove(node);  // this EventHandler remove the node from the StackPane
        });
    }
}