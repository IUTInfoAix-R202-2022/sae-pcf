package fr.univ_amu.iut.dialogs;

import fr.univ_amu.iut.ApplicationMain;
import fr.univ_amu.iut.bundle.Bundleable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;

import java.awt.desktop.AppForegroundListener;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class SettingsContent extends DialogPane implements Bundleable {

    @FXML
    private ChoiceBox choiceBox;

    @FXML
    private ToggleButton toggleButton;

    @FXML
    private Label changeLanguageLabel;

    @FXML
    private Label darkThemeLabel;

    private ButtonType submitButtonType = new ButtonType("Soumettre", ButtonBar.ButtonData.OK_DONE);
    private ButtonType cancelButtonType = new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE);

    public SettingsContent() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/fr/univ_amu/iut/javaFX/dialogs/Settings.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }



        this.getButtonTypes().addAll(cancelButtonType,submitButtonType);

        choiceBox.getItems().addAll(ApplicationMain.getAvailableLanguage());
        if (Locale.getDefault().toString().equals("fr")){
            choiceBox.setValue("Français");
        }
        else if (Locale.getDefault().toString().equals("en")){
            choiceBox.setValue("English");
        }

        generateBundle();
    }

    @FXML
    public void changeTheme(){
        if (toggleButton.isSelected()){
            if (!ApplicationMain.DARKTHEMEENABLED) {
                toggleButton.setText("Thème clair");
                ApplicationMain.DARKTHEMEENABLED = true;
                ApplicationMain.loadACSSFile("/fr/univ_amu/iut/applicationfx/darkTheme.css");
            }
            else {
                toggleButton.setText("Thème sombre");
                ApplicationMain.DARKTHEMEENABLED = false;
                //getStylesheets().remove();

            }
        }
    }

    public ChoiceBox getChoiceBox(){
        return this.choiceBox;
    }

    public ToggleButton getToggleButton() {
        return toggleButton;
    }

    @Override
    public void generateBundle(){
        ResourceBundle resourceBundle = ResourceBundle.getBundle("languages/settings");

        changeLanguageLabel.setText(resourceBundle.getString("changeLanguage"));

        darkThemeLabel.setText(resourceBundle.getString("darkTheme"));

        toggleButton.setText(resourceBundle.getString("darkTheme"));

        submitButtonType = new ButtonType(resourceBundle.getString("submit"), ButtonBar.ButtonData.OK_DONE);
        cancelButtonType = new ButtonType(resourceBundle.getString("cancel"), ButtonBar.ButtonData.CANCEL_CLOSE);
        this.getButtonTypes().setAll(submitButtonType,cancelButtonType);
    }
}
