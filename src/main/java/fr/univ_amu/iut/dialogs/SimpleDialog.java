package fr.univ_amu.iut.dialogs;

import fr.univ_amu.iut.bundle.Bundleable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;
import java.util.ResourceBundle;

public abstract class SimpleDialog implements Bundleable {
    Alert dialog;
    Optional<ButtonType> result;

    public SimpleDialog(){
        dialog = new Alert(Alert.AlertType.CONFIRMATION);
        dialog.setTitle("Confirmation");
    }

    public void setHeader(String header){
        dialog.setHeaderText(header);
    }

    public void setContent(String content){
        dialog.setContentText(content);
    }

    public void show(){
        result = dialog.showAndWait();
    }

    public ButtonType getResult() {
        return result.get();
    }

    @Override
    public void generateBundle(){
        ResourceBundle resourceBundle = ResourceBundle.getBundle("languages/dialogs/simpleDialog");

        dialog.setTitle(resourceBundle.getString("title"));
    }
}
