package fr.univ_amu.iut.dialogs;

import fr.univ_amu.iut.DAO.ConnectionManager;
import fr.univ_amu.iut.DAO.DAOTypology;
import fr.univ_amu.iut.DAO.JDBC.DAOActorIdentity;
import fr.univ_amu.iut.DAO.entities.ActorIdentity;
import fr.univ_amu.iut.DAO.entities.Typology;
import fr.univ_amu.iut.DAO.factory.DAOFactoryProducer;
import fr.univ_amu.iut.windows.DataEntry;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;

import java.util.*;

public class EditDataDialog extends DialogPane{
    private Alert alert = new Alert(Alert.AlertType.INFORMATION);
    private DataEntry dataEntry = new DataEntry();

    public EditDataDialog(){
        this.setContent(dataEntry);

        dataEntry.hideSubmitButton();

        alert.setDialogPane(this);

        ButtonType submitButton = new ButtonType("Soumettre", ButtonBar.ButtonData.OK_DONE);

        this.getButtonTypes().addAll(submitButton,new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE));
    }

    public void show(){
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE){
            if (!dataEntry.requiredFieldsManagement()){
                this.show();
            }
            ConfirmationDialog confirmation = new ConfirmationDialog("Sauvegarde dans la base de données.", "Les données saisis vont être enregistrées dans la base de données.");
            confirmation.show();
            if (confirmation.getResult() == ButtonType.OK) {
                DAOTypology daoTypology = DAOFactoryProducer.getFactory().createDaoTypology();
                ArrayList<ActorIdentity> initialActorIdentities = dataEntry.getActors(dataEntry.getTypology(daoTypology));
                DAOActorIdentity daoActorIdentity = DAOFactoryProducer.getFactory().createDaoActorIdentity();

                Typology typology = dataEntry.getTypology(daoTypology);
                daoTypology.update(typology);

                ArrayList<ActorIdentity> actorIdentities = dataEntry.getActors(typology);

                if (initialActorIdentities.size() > actorIdentities.size()) {
                    for (ActorIdentity actorIdentity : initialActorIdentities) {
                        daoActorIdentity.delete(actorIdentity);
                    }
                    for (ActorIdentity actorIdentity : actorIdentities) {
                        daoActorIdentity.insert(actorIdentity);
                    }
                } else {
                    for (int i = 0; i < initialActorIdentities.size(); ++i) {
                        if (!initialActorIdentities.get(i).getName().equals(actorIdentities.get(i).getName())) {
                            actorIdentities.get(i).setId(initialActorIdentities.get(i).getId());
                            daoActorIdentity.update(actorIdentities.get(i));
                        }
                    }
                    for (int i = initialActorIdentities.size(); i < actorIdentities.size(); ++i) {
                        daoActorIdentity.insert(actorIdentities.get(i));
                    }
                }

                ConnectionManager.getInstance().commit();

                dataEntry.clearFields();
            }
            else{
                this.show();
            }
        }
    }

    public void setTuple(String[] tuple){
        this.dataEntry.setTextChoiceBoxThemeOfUse(tuple[1]);
        this.dataEntry.setTextChoiceBoxDiscipline(tuple[2]);
        this.dataEntry.setTextChoiceBoxDegree(tuple[3]);
        this.dataEntry.setTextChoiceBoxAcademy(tuple[4]);
        this.dataEntry.setTextChoiceBoxAcademicRegion(tuple[5]);
        this.dataEntry.setFieldTypeOfActors(tuple[6]);
        this.dataEntry.setFieldResourceLink(tuple[7]);
        this.dataEntry.setFieldResourceName(tuple[8]);
        this.dataEntry.setFieldSourceType(tuple[9]);
        this.dataEntry.setFieldComment(tuple[10]);

        String[] names = new String[tuple.length-11];
        for (int i = 0; i < names.length; ++i) {
            names[i] = tuple[i+11];
        }
        this.dataEntry.setNames(names);
    }


}
