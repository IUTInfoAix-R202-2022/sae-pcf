package fr.univ_amu.iut.windows;

import fr.univ_amu.iut.DAO.DAOTypology;
import fr.univ_amu.iut.DAO.entities.Typology;
import fr.univ_amu.iut.DAO.factory.DAOFactoryProducer;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

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
            Tabs.getInstance().showLoading("résultat "+this.name);

            Executors.newCachedThreadPool().execute(new Runnable() {
                @Override
                public void run() {
                    butonAction();   //Run on another thread to have a loading bar
                }
            });
        });
    }

    private void butonAction(){

        DAOTypology daoTypology = DAOFactoryProducer.getFactory().createDaoTypology();
        List<Typology> typologies = daoTypology.findByThemeId(this.id);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {  //run on fx thread because it's GUI change
                Tabs.getInstance().getLoadingDialog().addProgressToProgressBar(0.2);
            }
        });

        List<String[]> stringsList = new ArrayList<>();
        for (Typology typology : typologies){
            stringsList.add(Typology.getStrings(typology));
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    Tabs.getInstance().getLoadingDialog().addProgressToProgressBar(0.8 / typologies.size()); //run on fx thread because it's GUI change
                    Tabs.getInstance().getLoadingDialog().setLoadingDetails("Chargement de la ressource \""+typology.getResourceName()+"\"");
                }
            });
        }

        Platform.runLater(new Runnable() {
            @Override
            public void run() {   //run on fx thread because it's GUI change
                ResultsTab resultsTab = new ResultsTab();
                Results results = new Results();
                results.addResults(stringsList);
                resultsTab.getChildren().add(results);

                Tabs.getInstance().addATab(name,resultsTab,true,null);

                Tabs.getInstance().getLoadingDialog().endLoad();
            }
        });
    }
}
