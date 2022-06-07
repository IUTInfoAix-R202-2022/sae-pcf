package fr.univ_amu.iut.windows;

import fr.univ_amu.iut.ApplicationMain;
import fr.univ_amu.iut.DAO.ConnectionManager;
import fr.univ_amu.iut.DAO.DAOAcademy;
import fr.univ_amu.iut.DAO.DAOThemeOfUse;
import fr.univ_amu.iut.DAO.DAOTypology;
import fr.univ_amu.iut.DAO.JDBC.DAOActorIdentity;
import fr.univ_amu.iut.DAO.entities.Academy;
import fr.univ_amu.iut.DAO.entities.ActorIdentity;
import fr.univ_amu.iut.DAO.entities.ThemeOfUse;
import fr.univ_amu.iut.DAO.factory.DAOFactoryProducer;
import fr.univ_amu.iut.bundle.BundleManager;
import fr.univ_amu.iut.dialogs.ConfirmationDialog;
import fr.univ_amu.iut.dialogs.EditDataDialog;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextAlignment;

import java.io.IOException;
import java.util.List;

public class Results extends GridPane {

    private int tabIndex;

    public Results(int tabIndex) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/fr/univ_amu/iut/javaFX/windows/Results.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        this.setHgap(8);
        this.setVgap(5);


        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.tabIndex = tabIndex;
    }

    public void addResults(List<String[]> results, String type) {
        for (int i = 0; i < results.size(); ++i) {
            for (int j = 0; j <= 3; ++j) {
                Button data;
                switch (j){
                    case(0):
                        data = new Button(results.get(i)[9]);
                        data.getStyleClass().add("IntituleButton");
                        break;
                    case(1):
                        data = new Button(results.get(i)[1]);
                        data.getStyleClass().add("ResultButton");
                        break;
                    case(2):
                        data = new Button(results.get(i)[2]);
                        data.getStyleClass().add("ResultButton");
                        break;
                    default:
                        data = new Button(results.get(i)[10]);
                        data.getStyleClass().add("ResultButton");

                }
                int finalI = i;
                data.setOnAction(
                        actionEvent -> {
                            Tabs.getInstance().addATab("Random tab name", new DetailedResult(results.get(finalI), Tabs.getInstance().getTabPaneSize()), true, "detailedResult");
                            BundleManager.updateBundle();
                        }
                );

                data.setWrapText(true);
                data.setMaxWidth((ApplicationMain.WINDOW_WIDTH / 4 ));
                data.setTextAlignment(TextAlignment.CENTER);
                data.setMaxHeight(Double.POSITIVE_INFINITY);

                this.add(data, j, i+1);

            }
            if (Home.isConnected()) {  //Add admin MenuButton if the admin is connected
                MenuButton adminBtn = new MenuButton("...");
                adminBtn.setId("adminButton");
                setMargin(adminBtn, new Insets(0, 0, 0, 13));
                adminBtn.getStyleClass().add("adminBtn");


                MenuItem modify = new MenuItem("Modifier"); // modify button
                modify.setId("modifyMenuItem");

                int finalI1 = i;
                modify.setOnAction(actionEvent -> {   // event handler of the modify button
                    EditDataDialog editDialog = new EditDataDialog();
                    editDialog.setTuple(results.get(finalI1));
                    editDialog.show();
                });

                MenuItem delete = new MenuItem("Supprimer"); //delete button
                delete.setId("deleteMenuItem");

                delete.setOnAction(actionEvent -> { // event handler of the delete button
                    // Dialog for confirmation
                    ConfirmationDialog confirmation = new ConfirmationDialog("Suppression dans la base de données.", "Les données associées vont être supprimées de la base de données.");
                    confirmation.show();
                    if (confirmation.getResult() == ButtonType.OK) {

                        DAOThemeOfUse daoThemeOfUse = DAOFactoryProducer.getFactory().createDaoThemeOfUse();
                        ThemeOfUse actualTheme = daoThemeOfUse.getById(daoThemeOfUse.getByName(results.get(finalI1)[1]).getId());  //we get the current theme before commit changes on the database

                        DAOAcademy daoAcademy = DAOFactoryProducer.getFactory().createDAOAcademy();
                        Academy actualAcademy = Academy.findByName(daoAcademy.findAll(),results.get(finalI1)[4]);//we get the current academy before commit changes on the database

                        int typologyId = Integer.parseInt(results.get(finalI1)[0]);

                        DAOActorIdentity daoActorIdentity = DAOFactoryProducer.getFactory().createDaoActorIdentity();
                        List<ActorIdentity> actorIdentities = daoActorIdentity.getByTypologyId(typologyId);

                        for (ActorIdentity actorIdentityToDelete : actorIdentities) {
                            daoActorIdentity.delete(actorIdentityToDelete);
                        }

                        DAOTypology daoTypology = DAOFactoryProducer.getFactory().createDaoTypology();
                        daoTypology.delete(daoTypology.getById(typologyId));

                        ConnectionManager.getInstance().commit();
                        switch (type){
                            case "theme" :
                                Theme.addThemeTab(actualTheme);    //Add the new result tab without deleted raw
                                break;
                            case "academicTheme" :
                                AcademicTheme.addThemeTab(actualTheme, actualAcademy);
                                break;
                        }

                        Tabs.getInstance().remove(this.tabIndex); // Delete this tab because it's now deprecated
                    }
                });
                adminBtn.getItems().addAll(modify, delete); //add these buttons to the admin MenuButton
                this.add(adminBtn, 4, i + 1); // add this MenuButton to the result
            }
        }
    }
}
