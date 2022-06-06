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
import fr.univ_amu.iut.ApplicationMain;

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
import javafx.scene.text.TextAlignment;

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
                "Type d'acteurs", "Lien de la ressource", "Nom de la ressource",
                "Type de source", "Commentaires", "Identité des acteurs"};

        for (int i = 0; i < labels.length; ++i) {
            Label columnLabel = new Label(labels[i]);
            columnLabel.getStyleClass().add("columnLabel");
          
            VBox column = new VBox(columnLabel);

            if (i == labels.length-1){ // for display all the names of actors identities
                for (int j = i+1; j < singleResult.length; ++j) { // j = i +1 because singleResult[0] is the id
                    Label columnText = new Label(singleResult[j]);
                    columnText.getStyleClass().add("columnText");
                    column.getChildren().add(columnText);
                    columnText.setPadding(new Insets(5, 3, 3, 3));
                    columnText.getStyleClass().add("columnText");
                    columnText.setWrapText(true);
                    columnText.setMaxWidth((ApplicationMain.WINDOW_WIDTH / 4 ));
                    columnText.setTextAlignment(TextAlignment.CENTER);
                }
            }
            else {
                Label columnText = new Label(singleResult[i+1]);
                columnText.getStyleClass().add("columnText");
                column.getChildren().add(columnText);
                columnText.setPadding(new Insets(5, 3, 3, 3));
                columnText.getStyleClass().add("columnText");
                columnText.setWrapText(true);
                columnText.setMaxWidth((ApplicationMain.WINDOW_WIDTH / 4 ));
                columnText.setTextAlignment(TextAlignment.CENTER);
            }

            column.setAlignment(Pos.TOP_CENTER);
            column.getStyleClass().add("resultColumn");

            columnLabel.setMaxWidth(Double.MAX_VALUE);

            this.getChildren().add(column);
        }
        setHgap(13);
        setVgap(30);
        setAlignment(Pos.TOP_LEFT);
        setPadding(new Insets(13, 25, 0, 25));
        getStyleClass().add("DetailedTab");

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
