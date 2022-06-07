package fr.univ_amu.iut.windows;

import fr.univ_amu.iut.ApplicationMain;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.util.List;

public class ResultsTab extends ScrollPane {
    public ResultsTab() {
    }

    public void setResults(Node node) {
        VBox content = new VBox(node);
        content.getStyleClass().add("transparentBG");
        content.setPrefSize(ApplicationMain.WINDOW_WIDTH, ApplicationMain.WINDOW_HEIGHT);
        content.setAlignment(Pos.TOP_CENTER);
        this.setContent(content);
    }
}
