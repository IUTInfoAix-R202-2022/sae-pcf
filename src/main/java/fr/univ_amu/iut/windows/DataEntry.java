package fr.univ_amu.iut.windows;

import fr.univ_amu.iut.dialogs.ConfirmationDialog;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

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
    private VBox vBoxIdOfActor;

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

    private ArrayList<TextInputControl> fields;
    private ArrayList<TextInputControl> requiredFields;
    private ArrayList<Label> requiredFieldsNotFilledLabels;

    @FXML
    private void initialize() {
        fields = new ArrayList (List.of(new TextInputControl[]{fieldThemeOfUse, fieldDiscipline, fieldDegree, fieldAcademy, fieldAcademicRegion, fieldTypeOfActors, fieldIdOfActorName, fieldIdOfActorFirstName, fieldResourceLink, fieldResourceName, fieldSourceType, fieldComment}));
        requiredFields = new ArrayList (List.of(new TextInputControl[]{fieldThemeOfUse, fieldDiscipline, fieldDegree, fieldAcademy, fieldTypeOfActors, fieldIdOfActorName,fieldIdOfActorFirstName, fieldResourceLink}));
        //TextInputControl is the common parent of TextField and TextArea
        requiredFieldsNotFilledLabels = new ArrayList (List.of(new Label[]{labelNotFillThemeOfUse, labelNotFillDiscipline, labelNotFillDegree, labelNotFillAcademy, labelNotFillTypeOfActors, labelNotFillIdOfActorName,labelNotFillIdOfActorFirstName, labelNotFillResourceLink}));
    }

    private void cleardFields(){
        for (int i = 0; i < fields.size(); i++) {
            fields.get(i).clear();
        }
        for (int i = 0; i < requiredFieldsNotFilledLabels.size(); i++) {
            requiredFieldsNotFilledLabels.get(i).setVisible(false);
        }
    }

    private Boolean requiredFieldsManagement(){
        Boolean isRequiredFieldsFilled = true;
        for (int i = 0; i < requiredFields.size(); ++i) {
            if (requiredFields.get(i).getText().strip().equals("")){
                requiredFieldsNotFilledLabels.get(i).setVisible(true);
                if (isRequiredFieldsFilled){
                    isRequiredFieldsFilled = false;
                }
            }
            else {
                requiredFieldsNotFilledLabels.get(i).setVisible(false);
            }
        }
        return isRequiredFieldsFilled;
    }

    private HBox newHboxIdActor(){
        TextField name = new TextField();
        TextField firstName = new TextField();

        name.setPromptText("nom");
        firstName.setPromptText("prenom");

        HBox.setHgrow(name, Priority.ALWAYS);
        HBox.setHgrow(firstName, Priority.ALWAYS);

        fields.add(name);
        fields.add(firstName);
        requiredFields.add(name);
        requiredFields.add(firstName);

        HBox hbox = new HBox(name,firstName);
        hbox.setSpacing(20);


        return hbox;
    }

    private HBox newHboxIdActorNotFillLabel(){
        Label name = new Label("Veuillez remplir ce champ");
        Label firstName = new Label("Veuillez remplir ce champ");
        name.getStyleClass().add("error");
        firstName.getStyleClass().add("error");

        name.setVisible(false);
        firstName.setVisible(false);

        requiredFieldsNotFilledLabels.add(name);
        requiredFieldsNotFilledLabels.add(firstName);

        HBox hbox = new HBox(name,firstName);
        hbox.setSpacing(148);

        return hbox;
    }

    @FXML
    private void addIdOfActor(){
        if (vBoxIdOfActor.getChildren().size() == 3){
            Button removeButton = new Button("Supprimer un acteur");
            removeButton.setOnAction(actionEvent -> {
                vBoxIdOfActor.getChildren().remove(vBoxIdOfActor.getChildren().size() - 4, vBoxIdOfActor.getChildren().size() - 2);
                if (vBoxIdOfActor.getChildren().size() == 4) {
                    vBoxIdOfActor.getChildren().remove(vBoxIdOfActor.getChildren().size() - 1);
                }
                fields.remove(fields.size() - 1);
                fields.remove(fields.size() - 1);

                requiredFields.remove(requiredFields.size() - 1);
                requiredFields.remove(requiredFields.size() - 1);

                requiredFieldsNotFilledLabels.remove(requiredFieldsNotFilledLabels.size() - 1);
                requiredFieldsNotFilledLabels.remove(requiredFieldsNotFilledLabels.size() - 1);
            });
            vBoxIdOfActor.getChildren().add(removeButton);
        }
        HBox fieldsId = newHboxIdActor();
        HBox labelsId = newHboxIdActorNotFillLabel();

        vBoxIdOfActor.getChildren().add(vBoxIdOfActor.getChildren().size()-2,fieldsId);
        vBoxIdOfActor.getChildren().add(vBoxIdOfActor.getChildren().size()-2,labelsId);

    }

    @FXML
    private void submitNewInnovation(){
        System.out.println("SubmitNewDataButton clicked");
        if (requiredFieldsManagement()){
            ConfirmationDialog confirmation = new ConfirmationDialog("Sauvegarde dans la base de données.","Les données saisis vont être enregistrées dans la base de données.");
            confirmation.show();
            if (confirmation.getResult() == ButtonType.OK) {
                cleardFields();
                System.out.println("SubmitNewDataButton confirmed");
                //TODO Enregistrement dans la base de données
            }
        }
    }
}
