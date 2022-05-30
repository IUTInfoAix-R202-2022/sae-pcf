package fr.univ_amu.iut.windows;

import javafx.scene.control.Button;

public class Theme extends Button {
    Button theme;

    public Theme(String textButton) {
        theme = new Button(textButton);
        theme.getStyleClass().add("button");
        theme.getStyleClass().add("themeButton");
    }
}
