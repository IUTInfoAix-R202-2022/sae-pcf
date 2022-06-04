package fr.univ_amu.iut.windows;

import javafx.scene.control.Button;
import javafx.scene.text.TextAlignment;

public class Theme extends Button {
    private String name;

    public Theme(String textButton) {
        name = textButton;

        this.setText(this.name);
        this.getStyleClass().add("button");
        this.getStyleClass().add("themeButton");
        this.setWrapText(true);
        this.setTextAlignment(TextAlignment.CENTER);

        this.setOnAction(actionEvent -> {
            Tabs.getInstance().addATab(name,new ResultsTab(),true);
        });
    }
}
