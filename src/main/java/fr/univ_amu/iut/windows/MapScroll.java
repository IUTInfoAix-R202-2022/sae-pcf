package fr.univ_amu.iut.windows;

import fr.univ_amu.iut.DAO.DAOAcademy;
import fr.univ_amu.iut.DAO.entities.Academy;
import fr.univ_amu.iut.DAO.factory.DAOFactoryProducer;
import fr.univ_amu.iut.view.map.AcademiePath;
import fr.univ_amu.iut.view.map.France;
import fr.univ_amu.iut.view.map.FranceBuilder;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class MapScroll extends ScrollPane {
    public MapScroll() {
        France france = FranceBuilder.create()
                .backgroundColor(Color.web("#f4f4f4"))
                .fillColor(Color.web("#f4f4f4"))
                .strokeColor(Color.web("#00587d"))
                .hoverColor(Color.web("#5e00ed"))
                //.pressedColor(Color.web("#6cee85"))
                .selectedColor(Color.web("#202077"))
                .mousePressHandler(evt -> {
                    AcademiePath academiePath = (AcademiePath) evt.getSource();
                    DAOAcademy daoAcademy = DAOFactoryProducer.getFactory().createDAOAcademy();
                    System.out.println(academiePath.getAcademie().getNom());
                    Academy academy = Academy.findByName(daoAcademy.findAll(), academiePath.getAcademie().getNom().replace('\'',' '));
                    MapTab.getInstance().generateThemesScrollByAcademy(academy);
                })
                .selectionEnabled(true)
                .build();

        StackPane map = new StackPane(france);
        map.setBackground(new Background(new BackgroundFill(france.getBackgroundColor(), CornerRadii.EMPTY, Insets.EMPTY)));
        this.setContent(map);
        this.setMaxHeight(580);
    }
}
