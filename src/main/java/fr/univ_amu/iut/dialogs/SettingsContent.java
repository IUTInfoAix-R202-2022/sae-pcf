package fr.univ_amu.iut.dialogs;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;

import java.io.IOException;

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

        //TODO Dynamic selection of language
        choiceBox.getItems().addAll("Français","Anglais");
        choiceBox.setValue("Français");
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
}
