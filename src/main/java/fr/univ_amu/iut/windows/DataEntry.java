package fr.univ_amu.iut.windows;

import fr.univ_amu.iut.DAO.*;
import fr.univ_amu.iut.DAO.entities.*;
import fr.univ_amu.iut.DAO.factory.DAOFactoryProducer;
import fr.univ_amu.iut.dialogs.AddDataChoiceBoxDialog;
import fr.univ_amu.iut.dialogs.ConfirmationDialog;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.*;

public class DataEntry extends ScrollPane {

    private List<ThemeOfUse> themeOfUseList;
    private List<Discipline> disciplines;
    private List<Degree> degrees;
    private List<Academy> academies;
    private List<AcademicRegion> academicRegions;

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
    private ChoiceBox choiceBoxThemeOfUse;

    @FXML
    private Label labelNotFillThemeOfUse;

    @FXML
    private ChoiceBox choiceBoxDiscipline;

    @FXML
    private Label labelNotFillDiscipline;

    @FXML
    private ChoiceBox choiceBoxDegree;

    @FXML
    private Label labelNotFillDegree;

    @FXML
    private ChoiceBox choiceBoxAcademy;

    @FXML
    private Label labelNotFillAcademy;

    @FXML
    private ChoiceBox choiceBoxAcademicRegion;

    @FXML
    private TextField fieldTypeOfActors;

    @FXML
    private Label labelNotFillTypeOfActors;

    @FXML
    private VBox vBoxIdOfActor;

    @FXML
    private TextField fieldIdOfActorName;

    @FXML
    private Label labelNotFillIdOfActorName;

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

    private ArrayList<StringProperty> fieldsStringProperties = new ArrayList<StringProperty>();
    private ArrayList<StringProperty> requiredFieldsStringProperties = new ArrayList<StringProperty>();
    private ArrayList<Label> requiredFieldsNotFilledLabels;

    @FXML
    private void initialize() {
        initializeFieldsStringProperties();

        initializeRequiredFieldsStringProperties();

        requiredFieldsNotFilledLabels = new ArrayList (List.of(new Label[]{labelNotFillThemeOfUse, labelNotFillDiscipline, labelNotFillDegree, labelNotFillAcademy, labelNotFillTypeOfActors, labelNotFillIdOfActorName, labelNotFillResourceLink}));

        initializeChoiceBox();
    }

    void initializeFieldsStringProperties(){
        for (int i = 0; i < 11; ++i) {
            fieldsStringProperties.add(new SimpleStringProperty(""));
        }
        //bindBidirectional choices box
        choiceBoxThemeOfUse.valueProperty().bindBidirectional(fieldsStringProperties.get(0));
        choiceBoxDiscipline.valueProperty().bindBidirectional(fieldsStringProperties.get(1));
        choiceBoxDegree.valueProperty().bindBidirectional(fieldsStringProperties.get(2));
        choiceBoxAcademy.valueProperty().bindBidirectional(fieldsStringProperties.get(3));
        choiceBoxAcademicRegion.valueProperty().bindBidirectional(fieldsStringProperties.get(4));

        //bindBidirectional texts fields
        fieldTypeOfActors.textProperty().bindBidirectional(fieldsStringProperties.get(5));
        fieldIdOfActorName.textProperty().bindBidirectional(fieldsStringProperties.get(10));
        fieldResourceLink.textProperty().bindBidirectional(fieldsStringProperties.get(6));
        fieldResourceName.textProperty().bindBidirectional(fieldsStringProperties.get(7));
        fieldSourceType.textProperty().bindBidirectional(fieldsStringProperties.get(8));

        //bindBidirectional texts areas
        fieldComment.textProperty().bindBidirectional(fieldsStringProperties.get(9));
    }

    void initializeRequiredFieldsStringProperties(){
        for (int i = 0; i < 7; ++i) {
            requiredFieldsStringProperties.add(new SimpleStringProperty(""));
        }

        //bindBidirectional choices box
        choiceBoxThemeOfUse.valueProperty().bindBidirectional(requiredFieldsStringProperties.get(0));
        choiceBoxDiscipline.valueProperty().bindBidirectional(requiredFieldsStringProperties.get(1));
        choiceBoxDegree.valueProperty().bindBidirectional(requiredFieldsStringProperties.get(2));
        choiceBoxAcademy.valueProperty().bindBidirectional(requiredFieldsStringProperties.get(3));

        //bindBidirectional texts fields
        fieldTypeOfActors.textProperty().bindBidirectional(requiredFieldsStringProperties.get(4));
        fieldIdOfActorName.textProperty().bindBidirectional(requiredFieldsStringProperties.get(5));
        fieldResourceLink.textProperty().bindBidirectional(requiredFieldsStringProperties.get(6));
    }

    void initializeChoiceBox(){
        themeOfUseList = DAOFactoryProducer.getFactory().createDaoThemeOfUse().findAll();

        disciplines = DAOFactoryProducer.getFactory().createDAODiscipline().findAll();

        degrees = DAOFactoryProducer.getFactory().createDaoDegree().findAll();

        academies = DAOFactoryProducer.getFactory().createDAOAcademy().findAll();

        academicRegions = DAOFactoryProducer.getFactory().createDAOAcademicRegion().findAll();

        addTextChoiceBoxThemeOfUse(themeOfUseList);
        addTextChoiceBoxDiscipline(disciplines);
        addTextChoiceBoxDegree(degrees);
        addTextChoiceBoxAcademy(academies);
        addTextChoiceBoxAcademicRegion(academicRegions);
    }

    public void clearFields(){
        for (int i = 0; i < fieldsStringProperties.size(); ++i) {
            fieldsStringProperties.get(i).set("");
        }
        for (int i = 0; i < requiredFieldsNotFilledLabels.size(); ++i) {
            requiredFieldsNotFilledLabels.get(i).setVisible(false);
        }
    }

    public Boolean requiredFieldsManagement(){
        Boolean isRequiredFieldsFilled = true;
        for (int i = 0; i < requiredFieldsStringProperties.size(); ++i) {
            if (requiredFieldsStringProperties.get(i).get().strip().equals("")){
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

    private HBox newHBoxIdActor(){
        TextField name = new TextField();

        name.setPromptText("Nom");

        HBox.setHgrow(name, Priority.ALWAYS);

        StringProperty nameStringProperty = new SimpleStringProperty("");
        name.textProperty().bindBidirectional(nameStringProperty); // We bindBidirectional with a StringProperty
        fieldsStringProperties.add(nameStringProperty); // We add this StringProperty to the ArrayList of string properties

        requiredFieldsStringProperties.add(nameStringProperty); // We add this StringProperty to the ArrayList of required string properties

        HBox hbox = new HBox(name);
        hbox.setSpacing(20);


        return hbox;
    }

    private HBox newHBoxIdActorNotFillLabel(){
        Label name = new Label("Veuillez remplir ce champ");
        name.getStyleClass().add("error");

        name.setVisible(false);

        requiredFieldsNotFilledLabels.add(name);

        HBox hbox = new HBox(name);
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
                fieldsStringProperties.remove(fieldsStringProperties.size() - 1);

                requiredFieldsStringProperties.remove(requiredFieldsStringProperties.size() - 1);

                requiredFieldsNotFilledLabels.remove(requiredFieldsNotFilledLabels.size() - 1);
            });
            vBoxIdOfActor.getChildren().add(removeButton);
        }
        HBox fieldsId = newHBoxIdActor();
        HBox labelsId = newHBoxIdActorNotFillLabel();

        vBoxIdOfActor.getChildren().add(vBoxIdOfActor.getChildren().size()-2,fieldsId);
        vBoxIdOfActor.getChildren().add(vBoxIdOfActor.getChildren().size()-2,labelsId);

    }

    @FXML
    private void submit(){
        if (requiredFieldsManagement()){
            ConfirmationDialog confirmation = new ConfirmationDialog("Sauvegarde dans la base de données.","Les données saisis vont être enregistrées dans la base de données.");
            confirmation.show();
            if (confirmation.getResult() == ButtonType.OK) {
                DAOTypology daoTypology = DAOFactoryProducer.getFactory().createDaoTypology();
                Typology typology = generateTypology(daoTypology);
                daoTypology.insert(typology);
                this.addActors(typology);

                ConnectionManager.getInstance().commit();

                clearFields();
            }
        }
    }

    private Typology generateTypology(DAOTypology daoTypology){
        Typology typology = new Typology();

        typology.setId(daoTypology.getNextId());

        typology.setIdThemeOfUse(ThemeOfUse.findByName(this.themeOfUseList,getFieldsString()[0]).getId());
        typology.setIdDiscipline(Discipline.findByName(this.disciplines,getFieldsString()[1]).getId());
        typology.setIdDegree(Degree.findByName(this.degrees,getFieldsString()[2]).getId());
        typology.setIdAcademy(Academy.findByName(this.academies,getFieldsString()[3]).getId());
        if (!Objects.equals(getFieldsString()[4],"")){
            typology.setIdAcademicRegion(AcademicRegion.findByName(this.academicRegions,getFieldsString()[4]).getId());
        }
        typology.setActorType(getFieldsString()[5]);
        typology.setLink(getFieldsString()[6]);
        typology.setResourceName(getFieldsString()[7]);
        typology.setResourceType(getFieldsString()[8]);
        typology.setCommentary(getFieldsString()[9]);

        return typology;
    }

    public Typology getTypology(DAOTypology daoTypology){
        Typology typology = new Typology();

        typology.setId(Typology.findByResourceName(daoTypology.findAll(),getFieldsString()[7]).getId());

        typology.setIdThemeOfUse(ThemeOfUse.findByName(this.themeOfUseList,getFieldsString()[0]).getId());
        typology.setIdDiscipline(Discipline.findByName(this.disciplines,getFieldsString()[1]).getId());
        typology.setIdDegree(Degree.findByName(this.degrees,getFieldsString()[2]).getId());
        typology.setIdAcademy(Academy.findByName(this.academies,getFieldsString()[3]).getId());
        if (!Objects.equals(getFieldsString()[4],"")){
            typology.setIdAcademicRegion(AcademicRegion.findByName(this.academicRegions,getFieldsString()[4]).getId());
        }
        typology.setActorType(getFieldsString()[5]);
        typology.setLink(getFieldsString()[6]);
        typology.setResourceName(getFieldsString()[7]);
        typology.setResourceType(getFieldsString()[8]);
        typology.setCommentary(getFieldsString()[9]);

        return typology;
    }

    private void addActors(Typology typology){
        DAOActorIdentity daoActorIdentity = DAOFactoryProducer.getFactory().createDaoActorIdentity();
        for (int i = 10; i < getFieldsString().length; ++i){
            ActorIdentity actorIdentity = new ActorIdentity();
            actorIdentity.setId(daoActorIdentity.getNextId());
            actorIdentity.setIdTypo(typology.getId());
            actorIdentity.setName(getFieldsString()[i]);

            daoActorIdentity.insert(actorIdentity);
        }
    }

    public ArrayList<ActorIdentity> getActors(Typology typology){

        DAOActorIdentity daoActorIdentity = DAOFactoryProducer.getFactory().createDaoActorIdentity();

        ArrayList<ActorIdentity> actorIdentities = new ArrayList<ActorIdentity>();
        for (int i = 10; i < getFieldsString().length; ++i){
            ActorIdentity actorIdentity = new ActorIdentity();
            actorIdentity.setId(daoActorIdentity.getNextId());
            actorIdentity.setIdTypo(typology.getId());
            actorIdentity.setName(getFieldsString()[i]);

            actorIdentities.add(actorIdentity);
        }
        return actorIdentities;
    }

    public String[] getFieldsString() { //return the array of strings of all controllers
        String[] returnArray = new String[fieldsStringProperties.size()];
        int i = 0;
        for (StringProperty stringProperty : fieldsStringProperties) {
            returnArray[i++] = stringProperty.get();
        }
        return returnArray;
    }

    @FXML
    private void addAThemeOfUse(){
        AddDataChoiceBoxDialog dialog = new AddDataChoiceBoxDialog("thematique d'usage");
        dialog.showAndWait();
        if ( ! dialog.getResult().equals("")){
            DAOThemeOfUse daoThemeOfUse = DAOFactoryProducer.getFactory().createDaoThemeOfUse();
            ThemeOfUse themeOfUse = new ThemeOfUse();
            themeOfUse.setId(daoThemeOfUse.getNextId());
            themeOfUse.setName((String) dialog.getResult());

            daoThemeOfUse.insert(themeOfUse);

            ConnectionManager.getInstance().commit();

            themeOfUseList = DAOFactoryProducer.getFactory().createDaoThemeOfUse().findAll();
            choiceBoxThemeOfUse.getItems().clear();
            addTextChoiceBoxThemeOfUse(themeOfUseList);

            if (choiceBoxThemeOfUse.getItems().contains(dialog.getResult())){
                choiceBoxThemeOfUse.setValue(dialog.getResult());
            }
        }
    }

    @FXML
    private void addADiscipline(){
        AddDataChoiceBoxDialog dialog = new AddDataChoiceBoxDialog("Discipline");
        dialog.showAndWait();
        if ( ! dialog.getResult().equals("")){
            DAODiscipline daoDiscipline = DAOFactoryProducer.getFactory().createDAODiscipline();
            Discipline discipline = new Discipline();
            discipline.setId(daoDiscipline.getNextId());
            discipline.setName((String) dialog.getResult());

            daoDiscipline.insert(discipline);

            ConnectionManager.getInstance().commit();

            disciplines = DAOFactoryProducer.getFactory().createDAODiscipline().findAll();
            choiceBoxDiscipline.getItems().clear();
            addTextChoiceBoxDiscipline(disciplines);

            if (choiceBoxDiscipline.getItems().contains(dialog.getResult())){
                choiceBoxDiscipline.setValue(dialog.getResult());
            }
        }
    }

    @FXML
    private void addADegree(){
        AddDataChoiceBoxDialog dialog = new AddDataChoiceBoxDialog("Degré");
        dialog.showAndWait();
        if ( ! dialog.getResult().equals("")){
            DAODegree daoDegree = DAOFactoryProducer.getFactory().createDaoDegree();
            Degree degree = new Degree();
            degree.setId(daoDegree.getNextId());
            degree.setName((String) dialog.getResult());

            daoDegree.insert(degree);

            ConnectionManager.getInstance().commit();

            degrees = DAOFactoryProducer.getFactory().createDaoDegree().findAll();
            choiceBoxDegree.getItems().clear();
            addTextChoiceBoxDegree(degrees);

            if (choiceBoxDegree.getItems().contains(dialog.getResult())){
                choiceBoxDegree.setValue(dialog.getResult());
            }
        }
    }

    @FXML
    private void addAnAcademy(){
        AddDataChoiceBoxDialog dialog = new AddDataChoiceBoxDialog("Academie");
        dialog.showAndWait();
        if ( ! dialog.getResult().equals("")){
            DAOAcademy daoAcademy = DAOFactoryProducer.getFactory().createDAOAcademy();
            Academy academy = new Academy();
            academy.setId(daoAcademy.getNextId());
            academy.setName((String) dialog.getResult());

            daoAcademy.insert(academy);

            ConnectionManager.getInstance().commit();

            academies = DAOFactoryProducer.getFactory().createDAOAcademy().findAll();
            choiceBoxAcademy.getItems().clear();
            addTextChoiceBoxAcademy(academies);

            if (choiceBoxAcademy.getItems().contains(dialog.getResult())){
                choiceBoxAcademy.setValue(dialog.getResult());
            }
        }
    }

    @FXML
    private void addAAcademicRegion(){
        AddDataChoiceBoxDialog dialog = new AddDataChoiceBoxDialog("Region academique");
        dialog.showAndWait();
        if ( ! dialog.getResult().equals("")){
            DAOAcademicRegion daoAcademicRegion = DAOFactoryProducer.getFactory().createDAOAcademicRegion();
            AcademicRegion academicRegion = new AcademicRegion();
            academicRegion.setId(daoAcademicRegion.getNextId());
            academicRegion.setName((String) dialog.getResult());

            daoAcademicRegion.insert(academicRegion);

            ConnectionManager.getInstance().commit();

            academicRegions = DAOFactoryProducer.getFactory().createDAOAcademicRegion().findAll();
            choiceBoxAcademicRegion.getItems().clear();
            addTextChoiceBoxAcademicRegion(academicRegions);

            if (choiceBoxAcademicRegion.getItems().contains(dialog.getResult())){
                choiceBoxAcademicRegion.setValue(dialog.getResult());
            }
        }
    }

    public void setTextChoiceBoxThemeOfUse(String textThemeOfUse) {
        this.choiceBoxThemeOfUse.setValue(textThemeOfUse);
    }

    public void addTextChoiceBoxThemeOfUse(String textThemeOfUse){
        this.choiceBoxThemeOfUse.getItems().add(textThemeOfUse);
    }

    public void addTextChoiceBoxThemeOfUse(List<ThemeOfUse> themeOfUses){
        for(ThemeOfUse themeOfUse : themeOfUses){
            this.choiceBoxThemeOfUse.getItems().add(themeOfUse.getName());
        }
    }

    public void setTextChoiceBoxDiscipline(String textDiscipline) {
        this.choiceBoxDiscipline.setValue(textDiscipline);
    }

    public void addTextChoiceBoxDiscipline(String textDiscipline){
        this.choiceBoxDiscipline.getItems().add(textDiscipline);
    }

    public void setTextChoiceBoxDegree(String textDegree) {
        this.choiceBoxDegree.setValue(textDegree);
    }

    public void addTextChoiceBoxDiscipline(List<Discipline> disciplines){
        for(Discipline discipline : disciplines){
            this.choiceBoxDiscipline.getItems().add(discipline.getName());
        }
    }

    public void addTextChoiceBoxDegree(String textDegree){
        this.choiceBoxDegree.getItems().add(textDegree);
    }

    public void addTextChoiceBoxDegree(List<Degree> degrees){
        for (Degree degree : degrees){
            this.choiceBoxDegree.getItems().add(degree.getName());
        }
    }

    public void setTextChoiceBoxAcademy(String textAcademy) {
        this.choiceBoxAcademy.setValue(textAcademy);
    }

    public void addTextChoiceBoxAcademy(String textAcademy){
        this.choiceBoxAcademy.getItems().add(textAcademy);
    }

    public void addTextChoiceBoxAcademy(List<Academy> academies){
        for (Academy academy : academies){
            this.choiceBoxAcademy.getItems().add(academy.getName());
        }
    }

    public void setTextChoiceBoxAcademicRegion(String textAcademicRegion) {
        this.choiceBoxAcademicRegion.setValue(textAcademicRegion);
    }

    public void addTextChoiceBoxAcademicRegion(String textAcademicRegion){
        this.choiceBoxAcademicRegion.getItems().add(textAcademicRegion);
    }

    public void addTextChoiceBoxAcademicRegion(List<AcademicRegion> academicRegionList){
        for (AcademicRegion academicRegion : academicRegionList){
            this.choiceBoxAcademicRegion.getItems().add(academicRegion.getName());
        }
    }

    public void setFieldTypeOfActors(String fieldTypeOfActors) {
        this.fieldTypeOfActors.setText(fieldTypeOfActors);
    }

    public void setNames(String[] names){
        this.fieldIdOfActorName.setText(names[0]);

        for (int i = 1; i < names.length;){
            addIdOfActor();
            this.fieldsStringProperties.get(this.fieldsStringProperties.size()-1).set(names[i++]);
        }
    }

    public void setFieldResourceLink(String fieldResourceLink) {
        this.fieldResourceLink.setText(fieldResourceLink);
    }

    public void setFieldResourceName(String fieldResourceName) {
        this.fieldResourceName.setText(fieldResourceName);
    }

    public void setFieldSourceType(String fieldSourceType) {
        this.fieldSourceType.setText(fieldSourceType);
    }

    public void setFieldComment(String fieldComment) {
        this.fieldComment.setText(fieldComment);
    }

    public void hideSubmitButton(){
        this.submitNewDataButton.setVisible(false);
    }

}
