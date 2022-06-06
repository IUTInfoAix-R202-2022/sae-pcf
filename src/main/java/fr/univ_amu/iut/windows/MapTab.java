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
        this.setPadding(new Insets(13, 13, 13, 13));
        this.themesScroll = new ScrollPane(new MapTheme(new Academy()));
        this.themesScroll.setMinWidth(550);
        this.themesScroll.setMaxHeight(580);
        this.getChildren().addAll(new MapScroll(), this.themesScroll);
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
