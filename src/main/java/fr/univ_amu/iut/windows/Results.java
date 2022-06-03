package fr.univ_amu.iut.windows;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.List;

public class Results extends GridPane {

    Tabs tabs;

    public Results(Tabs tabs) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/fr/univ_amu/iut/javaFX/windows/Results.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void addResults(List<String[]> results) {
        for (int i = 0; i < results.size(); i++) {
            for (int j = 0; j < results.get(i).length; j++) {
                Button data = new Button(results.get(i)[j]);
                if (j==1)
                    data.getStyleClass().add("IntituleButton");
                else
                    data.getStyleClass().add("ResultButton");
                data.setOnAction(
                        actionEvent -> tabs.addATab("Random tab name", new DetailledResult(), true)
                );
                this.add(data, j, i);
            }
        }
    }
}
