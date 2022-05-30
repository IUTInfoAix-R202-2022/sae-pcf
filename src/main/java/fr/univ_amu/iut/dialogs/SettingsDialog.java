package fr.univ_amu.iut.dialogs;

import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;

public class SettingsDialog extends Dialog {

    private DialogPane dialogPane;

    public SettingsDialog(){
        dialogPane = new SettingsContent();
        this.setDialogPane(dialogPane);
    }

}
