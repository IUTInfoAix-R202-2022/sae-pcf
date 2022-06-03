package fr.univ_amu.iut.windows;

import javafx.scene.control.Button;

public class Theme extends Button {
    private String name;

    public Theme(String textButton) {
        name = textButton;

        this.setText(this.name);
        this.getStyleClass().add("button");
        this.getStyleClass().add("themeButton");

        this.setOnAction(actionEvent -> {
            Tabs.getInstance().addATab(name,new ResultsTab(),true);
        });
    }
}
