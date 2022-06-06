package fr.univ_amu.iut.windows;

import fr.univ_amu.iut.ApplicationMain;
import fr.univ_amu.iut.dialogs.EditDataDialog;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.beans.EventHandler;


public class DetailedResult extends FlowPane {
    public DetailedResult(String[] singleResult) {
        String[] labels = {"Thématique d'usage", "Discipline ", "Degré", "Académie", "Région académique",
                "Type d'acteurs", "Identité des acteurs", "Lien de la ressource", "Nom de la ressource",
                "Type de source", "Commentaires"};
        for (int i = 0; i < labels.length; i++) {
            Label columnLabel = new Label(labels[i]);
            columnLabel.getStyleClass().add("columnLabel");

            Label columnText = new Label(singleResult[i+1]);
            columnText.getStyleClass().add("columnText");
            VBox column = new VBox(columnLabel, columnText);
            column.setAlignment(Pos.TOP_CENTER);
            column.getStyleClass().add("resultColumn");
            columnText.setWrapText(true);
            columnLabel.setMaxWidth(Double.MAX_VALUE);

            columnText.setPadding(new Insets(5, 3, 3, 3));
            columnLabel.setPadding(new Insets(3, 3, 3, 3));

            columnText.setMaxWidth((ApplicationMain.WINDOW_WIDTH / 4 ));
            columnText.setTextAlignment(TextAlignment.CENTER);

            this.getChildren().add(column);
        }
        setHgap(13);
        setVgap(30);
        setAlignment(Pos.TOP_LEFT);
        setPadding(new Insets(13, 25, 0, 25));
        getStyleClass().add("DetailedTab");

        if (Home.isConnected()) {
            Button modifyBtn = new Button("Modifier");

            modifyBtn.setOnAction(actionEvent -> {
                EditDataDialog editDialog = new EditDataDialog();
                editDialog.setTuple(singleResult);
                editDialog.show();
            });

            Button deleteBtn = new Button("Supprimer");
            //TODO eventHandler of delete button
            this.getChildren().add(new HBox(modifyBtn, deleteBtn));
        }
    }
}
