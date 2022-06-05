package fr.univ_amu.iut.dialogs;

import fr.univ_amu.iut.ApplicationMain;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.Locale;

public class SettingsContent extends DialogPane {

    @FXML
    ChoiceBox choiceBox;

    @FXML
    ToggleButton toggleButton;

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

        ButtonType submitButtonType = new ButtonType("Soumettre", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButtonType = new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE);
        this.getButtonTypes().addAll(cancelButtonType,submitButtonType);

        choiceBox.getItems().addAll(ApplicationMain.getAvailableLanguage());
        if (Locale.getDefault().toString().equals("fr")){
            choiceBox.setValue("Français");
        }
        else if (Locale.getDefault().toString().equals("en")){
            choiceBox.setValue("English");
        }
    }

    @FXML
    public void changeTheme(){
        if (toggleButton.isSelected()){
            toggleButton.setText("Désactiver");
        }
        else{
            toggleButton.setText("Activer");
        }
    }

    public ChoiceBox getChoiceBox(){
        return this.choiceBox;
    }
}
