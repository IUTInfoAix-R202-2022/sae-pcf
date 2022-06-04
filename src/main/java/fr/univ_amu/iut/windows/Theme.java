package fr.univ_amu.iut.windows;

import fr.univ_amu.iut.DAO.DAOTypology;
import fr.univ_amu.iut.DAO.entities.Typology;
import fr.univ_amu.iut.DAO.factory.DAOFactoryProducer;
import javafx.scene.control.Button;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;
import java.util.List;

public class Theme extends Button {
    private String name;
    private int id;

    public Theme(String textButton, int id) {
        name = textButton;
        this.id = id;

        this.setText(this.name);
        this.getStyleClass().add("button");
        this.getStyleClass().add("themeButton");
        this.setWrapText(true);
        this.setTextAlignment(TextAlignment.CENTER);

        this.setOnAction(actionEvent -> {
            ResultsTab resultsTab = new ResultsTab();
            Results results = new Results();

            DAOTypology daoTypology = DAOFactoryProducer.getFactory().createDaoTypology();
            List<Typology> typologies = daoTypology.findByThemeId(this.id);

            List<String[]> stringsList = new ArrayList<>();
            for (Typology typology : typologies){
                stringsList.add(Typology.getStrings(typology));
            }

            results.addResults(stringsList);
            resultsTab.getChildren().add(results);
            Tabs.getInstance().addATab(name,resultsTab,true);
        });
    }
}
