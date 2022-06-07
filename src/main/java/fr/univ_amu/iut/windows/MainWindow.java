package fr.univ_amu.iut.windows;

import fr.univ_amu.iut.ApplicationMain;
import fr.univ_amu.iut.bundle.BundleManager;
import fr.univ_amu.iut.dialogs.ContactDialog;
import fr.univ_amu.iut.dialogs.SettingsDialog;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

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
        SettingsDialog settingsDialog = new SettingsDialog();
        Optional<ButtonType> result = settingsDialog.showAndWait();

        if (result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE){
            if (settingsDialog.getChoiceBox().getValue().equals("Français")){
                Locale.setDefault(Locale.FRENCH);
            }
            else if (settingsDialog.getChoiceBox().getValue().equals("English")){
                Locale.setDefault(Locale.ENGLISH);
            }

            BundleManager.updateBundle();
        }
    }

    @FXML
    public void help() {
        if (Locale.getDefault() == Locale.FRENCH){
            if (Home.isConnected()){
                if (this.getChildren().get(1).equals(Tabs.getInstance())){
                    ApplicationMain.getInstance().addHelp(new ImageView(new Image("fr/univ_amu/iut/images/screenMainPageAdminFR.png")));
                }
                else{
                    ApplicationMain.getInstance().addHelp(new ImageView(new Image("fr/univ_amu/iut/images/screenHomePageFR.png")));
                }
            }
            else {
                if (this.getChildren().get(1).equals(Tabs.getInstance())){
                    ApplicationMain.getInstance().addHelp(new ImageView(new Image("fr/univ_amu/iut/images/screenMainPageFR.png")));
                }
                else{
                    ApplicationMain.getInstance().addHelp(new ImageView(new Image("fr/univ_amu/iut/images/screenHomePageFR.png")));
                }
            }
        }
        else if (Locale.getDefault() == Locale.ENGLISH){
            if (Home.isConnected()){
                if (this.getChildren().get(1).equals(Tabs.getInstance())){
                    ApplicationMain.getInstance().addHelp(new ImageView(new Image("fr/univ_amu/iut/images/screenMainPageAdminEN.png")));
                }
                else{
                    ApplicationMain.getInstance().addHelp(new ImageView(new Image("fr/univ_amu/iut/images/screenHomePageEN.png")));
                }
            }
            else {
                if (this.getChildren().get(1).equals(Tabs.getInstance())){
                    ApplicationMain.getInstance().addHelp(new ImageView(new Image("fr/univ_amu/iut/images/screenMainPageEN.png")));
                }
                else{
                    ApplicationMain.getInstance().addHelp(new ImageView(new Image("fr/univ_amu/iut/images/screenHomePageEN.png")));
                }
            }
        }
    }

    @FXML
    public void contact() {
        ContactDialog contactDialog = new ContactDialog("http://www.google.fr");
        contactDialog.show();
    }

}
