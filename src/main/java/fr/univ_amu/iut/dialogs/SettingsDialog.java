package fr.univ_amu.iut.dialogs;

import fr.univ_amu.iut.ApplicationMain;
import fr.univ_amu.iut.bundle.Bundleable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;

import java.util.ResourceBundle;

public class SettingsDialog extends Dialog implements Bundleable {

    private SettingsContent settingsContent;

    public SettingsDialog(){
        settingsContent = new SettingsContent();

        this.setDialogPane(settingsContent);

        if(ApplicationMain.DARKTHEMEENABLED){
            settingsContent.getToggleButton().setSelected(true);
        }
    }

    public ChoiceBox getChoiceBox(){
        return settingsContent.getChoiceBox();
    }

    @Override
    public void generateBundle() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("languages/settings");

        this.setTitle(resourceBundle.getString("title"));
    }
}
