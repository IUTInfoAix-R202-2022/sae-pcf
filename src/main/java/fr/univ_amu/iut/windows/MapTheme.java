package fr.univ_amu.iut.windows;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.util.List;

public class MapTheme extends GridPane {
    public MapTheme() {
        this.add(new Button("Thème 1"), 0,0);
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
}
