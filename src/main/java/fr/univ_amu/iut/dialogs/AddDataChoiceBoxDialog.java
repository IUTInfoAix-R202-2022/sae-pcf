package fr.univ_amu.iut.dialogs;

import fr.univ_amu.iut.DAO.DAO;
import fr.univ_amu.iut.DAO.DAOAcademy;
import fr.univ_amu.iut.DAO.DAOThemeOfUse;
import fr.univ_amu.iut.DAO.JDBC.DAOAcademicRegion;
import fr.univ_amu.iut.DAO.JDBC.DAODegree;
import fr.univ_amu.iut.DAO.JDBC.DAODiscipline;
import fr.univ_amu.iut.DAO.entities.*;
import fr.univ_amu.iut.DAO.factory.DAOFactoryProducer;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.text.Font;
import javafx.stage.StageStyle;

import java.util.ArrayList;
import java.util.List;


public class AddDataChoiceBoxDialog extends Dialog {

    private AddDataChoiceBoxContent dialogPane;
    private Button submitButton = new Button("Soumettre");
    private Button cancelButton = new Button("Annuler");

    public AddDataChoiceBoxDialog(String dataName){
        dialogPane = new AddDataChoiceBoxContent(dataName);
        this.setDialogPane(dialogPane);
        this.initStyle(StageStyle.UNDECORATED);

        this.submitButton.setFont(Font.font("JetBrains Mono Regular"));

        this.submitButton.setOnAction(actionEvent -> {
            String text = dialogPane.getTextField().getText();
            if (! text.strip().equals("")) {
                ArrayList<String> listFindAll = new ArrayList<String>();
                switch (dataName){
                    case "thematique d'usage":
                        DAOThemeOfUse daoThemeOfUse = DAOFactoryProducer.getFactory().createDaoThemeOfUse();
                        for ( ThemeOfUse themeOfUse :daoThemeOfUse.findAll() ) {
                            listFindAll.add(themeOfUse.getName());
                        }
                        break;
                    case "Academie":
                        DAOAcademy daoAcademy = DAOFactoryProducer.getFactory().createDAOAcademy();
                        for ( Academy academy :daoAcademy.findAll() ) {
                            listFindAll.add(academy.getName());
                        }
                        break;
                    case "Region academique":
                        DAOAcademicRegion daoAcademicRegion = DAOFactoryProducer.getFactory().createDAOAcademicRegion();
                        for ( AcademicRegion academicRegion :daoAcademicRegion.findAll() ) {
                            listFindAll.add(academicRegion.getName());
                        }
                        break;
                    case "Degrée":
                        DAODegree daoDegree = DAOFactoryProducer.getFactory().createDaoDegree();
                        for ( Degree degree :daoDegree.findAll() ) {
                            listFindAll.add(degree.getName());
                        }
                        break;
                    case "Discipline":
                        DAODiscipline daoDiscipline = DAOFactoryProducer.getFactory().createDAODiscipline();
                        for ( Discipline discipline :daoDiscipline.findAll() ) {
                            listFindAll.add(discipline.getName());
                        }
                        break;
                }
                if (listFindAll.contains(text)){
                    dialogPane.getErrorMessageLabel().setVisible(true);
                }
                else {
                    ConfirmationDialog confirmation = new ConfirmationDialog("Sauvegarde dans la base de données.", "Les données saisis vont être enregistrées dans la base de données.");
                    confirmation.show();
                    if (confirmation.getResult() == ButtonType.OK) {
                        dialogPane.getErrorMessageLabel().setVisible(false);
                        System.out.println(dialogPane.getTextField().getText());
                        this.setResult(dialogPane.getTextField().getText());
                        this.close();
                    }
                }
            }
        });

        this.cancelButton.setFont(Font.font("JetBrains Mono Regular"));

        this.cancelButton.setOnAction(actionEvent -> {
            this.setResult("");
            this.close();
        });

        this.dialogPane.getVBox().getChildren().addAll(submitButton,cancelButton);
    }

}
