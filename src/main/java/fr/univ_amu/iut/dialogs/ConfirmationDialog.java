package fr.univ_amu.iut.dialogs;

import fr.univ_amu.iut.ApplicationMain;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;

import java.io.IOException;
import java.util.Optional;

public class ConfirmationDialog extends SimpleDialog{
    public ConfirmationDialog(String header, String content) {
        super(header, content);
    }
}
