package fr.univ_amu.iut.windows;

import javafx.scene.control.Button;

public class Theme extends Button {
    public Theme(String textButton) {
        this.setText(textButton);
        this.getStyleClass().add("button");
        this.getStyleClass().add("themeButton");
    }
}
