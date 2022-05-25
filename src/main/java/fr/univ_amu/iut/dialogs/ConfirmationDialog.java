package fr.univ_amu.iut.dialogs;

import fr.univ_amu.iut.ApplicationMain;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;

import java.io.IOException;
import java.util.Optional;

public class ConfirmationDialog {
    Alert dialog;
    Optional<ButtonType> result;

    public ConfirmationDialog(String header, String content){
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
