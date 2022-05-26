package fr.univ_amu.iut.windows;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

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
    private Label labelNotFillIdOfActor;

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

    private final TextInputControl[] fields = {fieldThemeOfUse,fieldDiscipline,fieldDegree,fieldAcademy,fieldAcademicRegion,fieldTypeOfActors,fieldIdOfActorName,fieldIdOfActorFirstName,fieldResourceLink,fieldResourceName,fieldSourceType,fieldComment}; //TextInputControl is the common parent of TextField and TextArea
    private final TextInputControl[] requiredFields = {fieldThemeOfUse,fieldDiscipline,fieldDegree,fieldAcademy,fieldTypeOfActors,fieldIdOfActorName,fieldResourceLink}; //TextInputControl is the common parent of TextField and TextArea
    private final Label[] requiredFieldsNotFilledLabels = {labelNotFillThemeOfUse,labelNotFillDiscipline,labelNotFillDegree,labelNotFillAcademy,labelNotFillTypeOfActors,labelNotFillIdOfActor,labelNotFillResourceLink};

    @FXML
    private void initialize() {

    }

    private void cleardFields(){
        for (int i = 0; i < fields.length; i++) {
            fields[i].clear();
        }
        for (int i = 0; i < requiredFieldsNotFilledLabels.length; i++) {
            requiredFieldsNotFilledLabels[i].setVisible(false);
        }
    }

    private Boolean requiredFieldsManagment(){
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
    private void sumbitNewInnovation(){
        if (requiredFieldsManagment()){
            //TODO add in database
            cleardFields();
        }
        System.out.println("SubmitNewDataButton clicked");
    }
}
