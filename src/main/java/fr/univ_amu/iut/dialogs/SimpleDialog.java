package fr.univ_amu.iut.dialogs;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public abstract class SimpleDialog {
    Alert dialog;
    Optional<ButtonType> result;

    public SimpleDialog(String header, String content){
        dialog = new Alert(Alert.AlertType.CONFIRMATION);
        dialog.setTitle("Confirmation");
        dialog.setHeaderText(header);
        dialog.setContentText(content);
    }

    public void show(){
        result = dialog.showAndWait();
    }

    public ButtonType getResult() {
        return result.get();
    }
}
