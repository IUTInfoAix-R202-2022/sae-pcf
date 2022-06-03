package fr.univ_amu.iut.windows;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
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
        MenuButton adminBtn = new MenuButton("...");
        this.add(adminBtn, 4, 1);
    }

    public void addResults(List<String[]> results) {
        for (int i = 0; i < results.size(); i++) {
            for (int j = 0; j < results.get(i).length; j++) {
                if (j == 7 || j == 1 || j == 2 || j == 8) {
                    Button data = new Button(results.get(i)[j]);
                    if (j == 7)
                        data.getStyleClass().add("IntituleButton");
                    else
                        data.getStyleClass().add("ResultButton");
                    int finalI = i;
                    data.setOnAction(
                            actionEvent -> tabs.addATab("Random tab name", new DetailledResult(results.get(finalI)), true)
                    );
                    this.add(data, j, i);
                    MenuButton adminBtn = new MenuButton("...");
                    MenuItem modify = new MenuItem("Modifier");
                    //TODO eventHandler of the modify button
                    MenuItem delete = new MenuItem("Supprimer");
                    //TODO eventHandler of the delete button
                    adminBtn.getItems().addAll(modify, delete);
                }
            }
        }
    }
}
