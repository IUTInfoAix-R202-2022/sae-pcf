package fr.univ_amu.iut.windows;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
        Theme theme1 = new Theme("theme1");
        Theme theme2 = new Theme("theme2");
        Theme theme3 = new Theme("theme3");
        Theme theme4 = new Theme("theme4");
        Theme theme5 = new Theme("theme5");
        Theme theme6 = new Theme("theme6");
        Theme theme7 = new Theme("theme7");
        Theme theme8 = new Theme("theme1");
        Theme theme9 = new Theme("theme2");
        Theme theme10 = new Theme("theme3");
        Theme theme11= new Theme("theme4");
        Theme theme12 = new Theme("theme5");
        Theme theme13 = new Theme("theme6");
        Theme theme14 = new Theme("theme7");
        Theme theme15 = new Theme("theme7");
        Theme theme16 = new Theme("theme7");
        List<Theme> themelist = new ArrayList<>();
        Collections.addAll(themelist, theme1, theme2, theme3, theme4, theme5, theme6, theme7, theme8, theme9, theme10, theme11, theme12, theme13, theme14, theme15, theme16);
        addThemes(themelist);

    }

    public void addThemes(List<Theme> themeList) {
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

    public List<Theme> getThemes() {
        //TODO Implements getThemes() to connect to the SGBD
        return null;
    }
}
