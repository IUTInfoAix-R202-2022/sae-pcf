package fr.univ_amu.iut;

import fr.univ_amu.iut.DAO.ConnectionManager;
import fr.univ_amu.iut.DAO.JDBC.DAOAcademicRegion;
import fr.univ_amu.iut.DAO.entities.AcademicRegion;
import fr.univ_amu.iut.DAO.factory.DAOFactoryProducer;
import fr.univ_amu.iut.dialogs.EditDataDialog;
import fr.univ_amu.iut.windows.DataEntry;
import fr.univ_amu.iut.windows.Home;
import fr.univ_amu.iut.windows.Tabs;
import fr.univ_amu.iut.windows.MainWindow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ApplicationMain extends Application {

    private Scene root;
    private VBox mainWindow;
    private Tabs tabs;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        appBasis(stage);

        mainWindow = new MainWindow();
        mainWindow.getChildren().add(new Home(this));
        root = new Scene(mainWindow);
        loadCSS();

        stage.setScene(root);
        stage.show();
    }

    @Override
    public void stop(){
        ConnectionManager.getInstance().closeConnection();
    }

    private void appBasis(Stage stage){
        stage.setResizable(false);
        stage.setTitle("Titre de l'application");
        stage.setWidth(1200);
        stage.setHeight(700);

        tabs = new Tabs();
    }

    private void loadCSS(){
        root.getStylesheets().add("/fr/univ_amu/iut/applicationfx/Buttons.css");
        root.getStylesheets().add("/fr/univ_amu/iut/applicationfx/Tabs.css");
    }

    public void accessToData(){
        if (Home.isConnected()){
            tabs.addATab("Saisi",new DataEntry(),false);
        }
        mainWindow.getChildren().set(1,tabs);
    }
}
