package fr.univ_amu.iut.dialogs;

import fr.univ_amu.iut.windows.DataEntry;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;

import java.util.Optional;

public class EditDataDialog extends DialogPane{
    private Alert alert = new Alert(Alert.AlertType.INFORMATION);
    private DataEntry dataEntry = new DataEntry();

    public EditDataDialog(){
        this.setContent(dataEntry);

        alert.setDialogPane(this);

        this.getButtonTypes().addAll(new ButtonType("Soumettre", ButtonBar.ButtonData.OK_DONE),new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE));
    }

    public void show(){
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            //TODO update data base
        }
    }
}
