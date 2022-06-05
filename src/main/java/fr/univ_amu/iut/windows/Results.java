package fr.univ_amu.iut.windows;

import fr.univ_amu.iut.ApplicationMain;
import fr.univ_amu.iut.dialogs.EditDataDialog;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

import java.io.IOException;
import java.util.List;

public class Results extends GridPane {
    public Results() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/fr/univ_amu/iut/javaFX/windows/Results.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        this.setHgap(8);
        this.setVgap(5);

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
                switch (j){
                    case(0):
                        data = new Button(results.get(i)[9]);
                        data.getStyleClass().add("IntituleButton");
                        break;
                    case(1):
                        data = new Button(results.get(i)[1]);
                        data.getStyleClass().add("ResultButton");
                        break;
                    case(2):
                        data = new Button(results.get(i)[2]);
                        data.getStyleClass().add("ResultButton");
                        break;
                    default:
                        data = new Button(results.get(i)[10]);
                        data.getStyleClass().add("ResultButton");

                }
                int finalI = i;
                data.setOnAction(
                        actionEvent -> Tabs.getInstance().addATab("Random tab name", new DetailedResult(results.get(finalI)), true,null)
                );

                data.setWrapText(true);
                data.setMaxWidth((ApplicationMain.WINDOW_WIDTH / 4 ));
                data.setTextAlignment(TextAlignment.CENTER);
                this.add(data, j, i+1);

            }
            MenuButton adminBtn = new MenuButton("...");
            setMargin(adminBtn, new Insets(0, 0, 0, 13));
            adminBtn.getStyleClass().add("adminBtn");
            MenuItem modify = new MenuItem("Modifier");

            int finalI1 = i;
            modify.setOnAction(actionEvent -> {
                EditDataDialog editDialog = new EditDataDialog();
                editDialog.setTuple(results.get(finalI1));
                editDialog.show();

            });

            MenuItem delete = new MenuItem("Supprimer");
            //TODO eventHandler of the delete button
            adminBtn.getItems().addAll(modify, delete);
            this.add(adminBtn,4,i+1);
        }
    }
}
