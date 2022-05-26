package fr.univ_amu.iut.windows;

import fr.univ_amu.iut.dialogs.ConfirmationDialog;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;

import java.io.IOException;
public class DataEntry extends ScrollPane {

    public DataEntry() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/fr/univ_amu/iut/javaFX/windows/DataEntry.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @FXML
    private TextField fieldThemeOfUse;

    @FXML
    private Label labelNotFillThemeOfUse;

    @FXML
    private TextField fieldDiscipline;

    @FXML
    private Label labelNotFillDiscipline;

    @FXML
    private TextField fieldDegree;

    @FXML
    private Label labelNotFillDegree;

    @FXML
    private TextField fieldAcademy;

    @FXML
    private Label labelNotFillAcademy;

    @FXML
    private TextField fieldAcademicRegion;

    @FXML
    private TextField fieldTypeOfActors;

    @FXML
    private Label labelNotFillTypeOfActors;

    @FXML
    private TextField fieldIdOfActorName;

    @FXML
    private TextField fieldIdOfActorFirstName;

    @FXML
    private Label labelNotFillIdOfActorName;

    @FXML
    private Label labelNotFillIdOfActorFirstName;

    @FXML
    private TextField fieldResourceLink;

    @FXML
    private Label labelNotFillResourceLink;

    @FXML
    private TextField fieldResourceName;

    @FXML
    private TextField fieldSourceType;

    @FXML
    private TextArea fieldComment;

    @FXML
    private Button submitNewDataButton;

    private TextInputControl[] fields;
    private TextInputControl[] requiredFields;
    private Label[] requiredFieldsNotFilledLabels;
    @FXML
    private void initialize() {
        fields = new TextInputControl[]{fieldThemeOfUse, fieldDiscipline, fieldDegree, fieldAcademy, fieldAcademicRegion, fieldTypeOfActors, fieldIdOfActorName, fieldIdOfActorFirstName, fieldResourceLink, fieldResourceName, fieldSourceType, fieldComment}; //TextInputControl is the common parent of TextField and TextArea
        requiredFields = new TextInputControl[]{fieldThemeOfUse, fieldDiscipline, fieldDegree, fieldAcademy, fieldTypeOfActors, fieldIdOfActorName,fieldIdOfActorFirstName, fieldResourceLink}; //TextInputControl is the common parent of TextField and TextArea
        requiredFieldsNotFilledLabels = new Label[]{labelNotFillThemeOfUse, labelNotFillDiscipline, labelNotFillDegree, labelNotFillAcademy, labelNotFillTypeOfActors, labelNotFillIdOfActorName,labelNotFillIdOfActorFirstName, labelNotFillResourceLink};
    }

    private void cleardFields(){
        for (int i = 0; i < fields.length; i++) {
            fields[i].clear();
        }
        for (int i = 0; i < requiredFieldsNotFilledLabels.length; i++) {
            requiredFieldsNotFilledLabels[i].setVisible(false);
        }
    }

    private Boolean requiredFieldsManagement(){
        Boolean isRequiredFieldsFilled = true;
        for (int i = 0; i < requiredFields.length; ++i) {
            if (requiredFields[i].getText().strip().equals("")){
                requiredFieldsNotFilledLabels[i].setVisible(true);
                if (isRequiredFieldsFilled){
                    isRequiredFieldsFilled = false;
                }
            }
            else {
                requiredFieldsNotFilledLabels[i].setVisible(false);
            }
        }
        return isRequiredFieldsFilled;
    }

    @FXML
    private void submitNewInnovation(){
        System.out.println("SubmitNewDataButton clicked");
        if (requiredFieldsManagement()){
            ConfirmationDialog confirmation = new ConfirmationDialog("Sauvegarde dans la base de données.","Les données saisis vont être enregistrer dans la base de données.");
            confirmation.show();
            if (confirmation.getResult() == ButtonType.OK) {
                cleardFields();
                System.out.println("SubmitNewDataButton confirmed");
                //TODO Enregistrement dans la base de données
            }
        }
    }
}
