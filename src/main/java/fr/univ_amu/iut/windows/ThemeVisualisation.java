package fr.univ_amu.iut.windows;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ThemeVisualisation extends GridPane {

    private GridPane gridPane;

    public ThemeVisualisation() {
        gridPane = new GridPane();
        gridPane.setPrefHeight(600);
        gridPane.setPrefWidth(900);
        gridPane.setHgap(13);
        gridPane.setVgap(13);
        Button theme1 = new Button("theme1");
        Button theme2 = new Button("theme2");
        Button theme3 = new Button("theme3");
        Button theme4 = new Button("theme4");
        Button theme5 = new Button("theme5");
        Button theme6 = new Button("theme6");
        Button theme7 = new Button("theme7");
        List<Button> themelist = new ArrayList<>();
        Collections.addAll(themelist, theme1, theme2, theme3, theme4, theme5, theme6, theme7);
        addThemes(themelist);

    }

    public GridPane getGridPane() {
        return gridPane;
    }

    public void addThemes(List<Button> themeList) {
        int column = 0, row = 0;
        
        for (int i = 0; i < themeList.size(); i++, column++) {
            if (i != 0 && i%5==0) {
                ++row;
                column = 0;
            }
            gridPane.add(themeList.get(i), column, row);
        }
    }

    public List<Button> getThemes() {
        //TODO Implements getThemes() to connect to the SGBD
        return null;
    }
}
