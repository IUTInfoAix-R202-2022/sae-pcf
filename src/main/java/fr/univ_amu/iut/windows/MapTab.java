package fr.univ_amu.iut.windows;

import javafx.geometry.Insets;
import javafx.scene.layout.*;

public class MapTab extends HBox {
    public MapTab () {
        this.setSpacing(25);
        this.setPadding(new Insets(13, 13, 0, 13));
        this.getChildren().addAll(new MapScroll(), new MapTheme());
    }
}
