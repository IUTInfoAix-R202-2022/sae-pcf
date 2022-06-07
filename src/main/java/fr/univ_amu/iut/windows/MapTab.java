package fr.univ_amu.iut.windows;

import fr.univ_amu.iut.DAO.entities.Academy;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;

public class MapTab extends HBox {
    private static MapTab instance;
    private ScrollPane themesScroll;

    private MapTab () {
        this.setSpacing(25);
        this.getStyleClass().add("transparentBG");
        this.setPadding(new Insets(13, 13, 13, 13));
        this.themesScroll = new ScrollPane(new MapTheme(new Academy()));

        this.themesScroll.setMinWidth(550);
        this.themesScroll.setMaxHeight(580);
        MapScroll mapScroll = new MapScroll();
        setMargin(mapScroll, new Insets(0, 0, 25, 0));
        this.getChildren().addAll(mapScroll, this.themesScroll);
    }

    public static MapTab getInstance(){
        if (instance == null){
            instance = new MapTab();
        }
        return instance;
    }

    public void generateThemesScrollByAcademy(Academy academy){
        this.themesScroll = new ScrollPane(new MapTheme(academy));
        this.themesScroll.setMinWidth(550);
        this.themesScroll.setMaxHeight(580);
        this.getChildren().remove(1);
        this.getChildren().add(this.themesScroll);
    }


}
