package fr.univ_amu.iut.windows;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ThemeVisualisation extends GridPane {

    public ThemeVisualisation() {
        this.setPrefWidth(1200);
        this.setAlignment(Pos.TOP_CENTER);
        //gridPane.setHgap(13);
        //gridPane.setVgap(13);
        Button theme1 = new Button("theme1");
        Button theme2 = new Button("theme2");
        Button theme3 = new Button("theme3");
        Button theme4 = new Button("theme4");
        Button theme5 = new Button("theme5");
        Button theme6 = new Button("theme6");
        Button theme7 = new Button("theme7");
        Button theme8 = new Button("theme1");
        Button theme9 = new Button("theme2");
        Button theme10 = new Button("theme3");
        Button theme11= new Button("theme4");
        Button theme12 = new Button("theme5");
        Button theme13 = new Button("theme6");
        Button theme14 = new Button("theme7");
        Button theme15 = new Button("theme7");
        Button theme16 = new Button("theme7");
        List<Button> themelist = new ArrayList<>();
        Collections.addAll(themelist, theme1, theme2, theme3, theme4, theme5, theme6, theme7, theme8, theme9, theme10, theme11, theme12, theme13, theme14, theme15, theme16);
        addThemes(themelist);

    }

    public void addThemes(List<Button> themeList) {
        int column = 0, row = 0;
        
        for (int i = 0; i < themeList.size(); i++, column++) {
            themeList.get(i).setPrefSize(200, 150);
            setMargin(themeList.get(i), new Insets(13));
            if (i != 0 && i%5==0) {
                ++row;
                column = 0;
            }
            this.add(themeList.get(i), column, row);
        }
    }

    public List<Button> getThemes() {
        //TODO Implements getThemes() to connect to the SGBD
        return null;
    }
}
