package fr.univ_amu.iut.dialogs;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;

public class SettingsDialog extends Dialog {

    private SettingsContent settingsContent;

    public SettingsDialog(){
        settingsContent = new SettingsContent();
        this.setDialogPane(settingsContent);
    }

    public ChoiceBox getChoiceBox(){
        return settingsContent.getChoiceBox();
    }

}
