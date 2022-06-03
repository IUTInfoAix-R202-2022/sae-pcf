package fr.univ_amu.iut.windows;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

import java.util.List;

public class ResultsTab extends VBox {
    public ResultsTab(Tabs tabs) {
        this.getChildren().add(new Results(tabs));
        this.setAlignment(Pos.TOP_CENTER);
    }
}
