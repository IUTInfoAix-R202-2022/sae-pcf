package fr.univ_amu.iut.windows;

import fr.univ_amu.iut.dialogs.EditDataDialog;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.beans.EventHandler;


public class DetailedResult extends FlowPane {
    public DetailedResult(String[] singleResult) {
        String[] labels = {"Thématique d'usage", "Discipline ", "Degré", "Académie", "Région académique",
                "Type d'acteurs", "Identité des acteurs", "Lien de la ressource", "Nom de la ressource",
                "Type de source", "Commentaires"};
        for (int i = 0; i < labels.length; i++) {
            Label columnLabel = new Label(labels[i]);
            columnLabel.getStyleClass().add("columnLabel");
            Text columnText = new Text(singleResult[i+1]);
            VBox vBox = new VBox(columnLabel, columnText);
            Insets paddingVBox = new Insets(0,0,0,2);
            vBox.setPadding(paddingVBox);
            vBox.setStyle("-fx-border-width : 2");
            vBox.setStyle("-fx-border-color :  white white white black");
            this.getChildren().add(vBox);
        }
        this.setHgap(13);
        this.setVgap(60);
        this.setAlignment(Pos.TOP_CENTER);

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
