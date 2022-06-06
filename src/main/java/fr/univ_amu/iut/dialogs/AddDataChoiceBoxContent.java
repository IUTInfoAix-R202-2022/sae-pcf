package fr.univ_amu.iut.dialogs;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class AddDataChoiceBoxContent extends DialogPane {

    @FXML
    private Label dialogNameLabel;

    @FXML
    private VBox vBox;

    @FXML
    private TextField textField;

    @FXML
    private Label errorMessageLabel;

    @FXML
    private TextArea detailsArea;

    public AddDataChoiceBoxContent(String dataName){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/fr/univ_amu/iut/javaFX/dialogs/AddDataChoiceBox.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        dialogNameLabel.setText("Ajouter un(e) " + dataName + " à la base de données");
        errorMessageLabel.setText("Ce(tte) " + dataName + " est déja présente dans la base de données");
        detailsArea.setText("Dans ce dialogue vous pouvez ecrire un(e) nouveau/nouvelle " + dataName + " qui sera ajouté(e) à la base de données");
        detailsArea.setBorder(Border.EMPTY);
        detailsArea.setBackground(Background.EMPTY);
    }

    public VBox getVBox(){
        return vBox;
    }

    public TextField getTextField() {
        return textField;
    }

    public Label getErrorMessageLabel() {
        return errorMessageLabel;
    }
}
