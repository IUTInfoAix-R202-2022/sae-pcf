package fr.univ_amu.iut.windows;

import fr.univ_amu.iut.view.map.AcademiePath;
import fr.univ_amu.iut.view.map.France;
import fr.univ_amu.iut.view.map.FranceBuilder;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class MapTab extends HBox {
    France france;

    public MapTab () {
        this.setSpacing(25);
        this.setPadding(new Insets(13, 13, 0, 13));
        this.getChildren().addAll(new MapScroll(), new MapTheme());
    }
}
