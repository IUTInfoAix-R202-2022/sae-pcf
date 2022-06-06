package fr.univ_amu.iut.windows;

import fr.univ_amu.iut.DAO.ConnectionManager;
import fr.univ_amu.iut.DAO.DAOThemeOfUse;
import fr.univ_amu.iut.DAO.DAOTypology;
import fr.univ_amu.iut.DAO.JDBC.DAOActorIdentity;
import fr.univ_amu.iut.DAO.entities.ActorIdentity;
import fr.univ_amu.iut.DAO.entities.ThemeOfUse;
import fr.univ_amu.iut.DAO.entities.Typology;
import fr.univ_amu.iut.DAO.factory.DAOFactoryProducer;
import fr.univ_amu.iut.dialogs.ConfirmationDialog;
import fr.univ_amu.iut.dialogs.EditDataDialog;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.List;


public class DetailedResult extends FlowPane {

    private int tabIndex;

    public DetailedResult(String[] singleResult, int tabIndex) {
        this.tabIndex = tabIndex;

        DAOTypology daoTypology = DAOFactoryProducer.getFactory().createDaoTypology();
        Typology typology = daoTypology.getById(Integer.parseInt(singleResult[0]));  //create typology from id

        DAOActorIdentity daoActorIdentity = DAOFactoryProducer.getFactory().createDaoActorIdentity();
        List<ActorIdentity> actorIdentities = daoActorIdentity.getByTypologyId(typology.getId());  //create list of actors from typology

        String[] labels = {"Thématique d'usage", "Discipline ", "Degré", "Académie", "Région académique",
                "Type d'acteurs", "Identité des acteurs", "Lien de la ressource", "Nom de la ressource",
                "Type de source", "Commentaires"};
        for (int i = 0; i < labels.length; ++i) {
            Label columnLabel = new Label(labels[i]);
            columnLabel.getStyleClass().add("columnLabel");
            VBox vBox = new VBox(columnLabel);
            if (i == 6){ // for display all the names of actors identities
                for (ActorIdentity actor: actorIdentities) {
                    Text columnText = new Text(actor.getName());
                    vBox.getChildren().add(columnText);
                }
            }
            else {
                Text columnText = new Text(singleResult[i+1]);
                vBox.getChildren().add(columnText);
            }
            Insets paddingVBox = new Insets(0,0,0,2);
            vBox.setPadding(paddingVBox);
            vBox.setStyle("-fx-border-width : 2");
            vBox.setStyle("-fx-border-color :  white white white black");
            this.getChildren().add(vBox);
        }
        this.setHgap(13);
        this.setVgap(60);
        this.setAlignment(Pos.TOP_CENTER);

        if (Home.isConnected()) {
            Button modifyBtn = new Button("Modifier");

            modifyBtn.setOnAction(actionEvent -> {
                EditDataDialog editDialog = new EditDataDialog();
                editDialog.setTuple(singleResult);
                editDialog.show();
            });

            Button deleteBtn = new Button("Supprimer");

            deleteBtn.setOnAction(actionEvent -> { // event handler of the delete button
                // Dialog for confirmation
                ConfirmationDialog confirmation = new ConfirmationDialog("Suppression dans la base de données.", "Les données associées vont être supprimées de la base de données.");
                confirmation.show();
                if (confirmation.getResult() == ButtonType.OK) {

                    DAOThemeOfUse daoThemeOfUse = DAOFactoryProducer.getFactory().createDaoThemeOfUse();
                    ThemeOfUse actualTheme = daoThemeOfUse.getById(daoThemeOfUse.getByName(singleResult[1]).getId());  //we get the current theme before commit changes on the database

                    for (ActorIdentity actorIdentityToDelete : actorIdentities) {
                        daoActorIdentity.delete(actorIdentityToDelete);
                    }

                    daoTypology.delete(typology);

                    ConnectionManager.getInstance().commit();

                    Tabs.getInstance().remove(this.tabIndex); // Delete this tab because it's now deprecated

                    Theme.addThemeTab(actualTheme);    //Add the new result tab without deleted raw
                }
            });

            this.getChildren().add(new HBox(modifyBtn, deleteBtn));
        }
    }
}
