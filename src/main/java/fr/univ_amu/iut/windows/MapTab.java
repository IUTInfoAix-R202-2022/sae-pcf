package fr.univ_amu.iut.windows;

import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;

public class MapTab extends HBox {
    public MapTab () {
        this.setSpacing(25);
        this.setPadding(new Insets(13, 13, 13, 13));
        ScrollPane themesScroll = new ScrollPane(new MapTheme());
        themesScroll.setMinWidth(550);
        themesScroll.setMaxHeight(580);
        this.getChildren().addAll(new MapScroll(), themesScroll);
    }
}
