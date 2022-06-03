package fr.univ_amu.iut.windows;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

import java.util.List;

public class ResultsTab extends VBox {
    public ResultsTab() {
        this.getChildren().add(new Results());
        this.setAlignment(Pos.TOP_CENTER);
    }
}
