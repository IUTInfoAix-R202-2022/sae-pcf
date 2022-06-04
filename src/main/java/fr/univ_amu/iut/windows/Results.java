package fr.univ_amu.iut.windows;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.List;

public class Results extends GridPane {
    public Results() {
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
        for (int i = 0; i < results.size(); ++i) {
            for (int j = 0; j <= 3; ++j) {
                Button data;
                switch (j) {
                    case (0) -> {
                        data = new Button(results.get(i)[9]);
                        data.getStyleClass().add("IntituleButton");
                    }
                    case (1) -> {
                        data = new Button(results.get(i)[1]);
                        data.getStyleClass().add("ResultButton");
                    }
                    case (2) -> {
                        data = new Button(results.get(i)[2]);
                        data.getStyleClass().add("ResultButton");
                    }
                    default -> {
                        data = new Button(results.get(i)[10]);
                        data.getStyleClass().add("ResultButton");
                    }
                }
                int finalI = i;
                data.setOnAction(
                        actionEvent -> Tabs.getInstance().addATab("Random tab name", new DetailedResult(results.get(finalI)), true)
                );
                this.add(data, j, i+1);

            }
            if (Home.isConnected()) {
                MenuButton adminBtn = new MenuButton("...");
                setMargin(adminBtn, new Insets(0, 0, 0, 13));
                adminBtn.getStyleClass().add("adminBtn");
                MenuItem modify = new MenuItem("Modifier");
                //TODO eventHandler of the modify button
                MenuItem delete = new MenuItem("Supprimer");
                //TODO eventHandler of the delete button
                adminBtn.getItems().addAll(modify, delete);
                this.add(adminBtn, 4, i + 1);
            }
        }
    }
}
