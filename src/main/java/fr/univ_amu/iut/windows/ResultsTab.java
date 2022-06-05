package fr.univ_amu.iut.windows;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class ResultsTab extends ScrollPane {
    public ResultsTab(Node result) {
        VBox content = new VBox(result);
        content.setAlignment(Pos.TOP_CENTER);
        this.setContent(content);
    }
}
