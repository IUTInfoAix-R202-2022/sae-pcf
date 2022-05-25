package fr.univ_amu.iut.dialogs;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class ContactDialog {
    Alert dialog;
    Optional<ButtonType> result;

    public ContactDialog(String header, String content){
        dialog = new Alert(Alert.AlertType.CONFIRMATION);
        dialog.setTitle("Confirmation");
        dialog.setHeaderText(header);
        dialog.setContentText(content);
    }
}
