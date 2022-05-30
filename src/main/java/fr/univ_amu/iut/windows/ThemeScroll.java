package fr.univ_amu.iut.windows;

import javafx.scene.control.ScrollPane;

public class ThemeScroll extends ScrollPane {
    public ThemeScroll () {
        this.setContent(new ThemeVisualisation());
    }
}
