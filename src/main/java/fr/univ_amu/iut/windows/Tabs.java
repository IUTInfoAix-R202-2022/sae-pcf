package fr.univ_amu.iut.windows;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class Tabs extends Pane {

    private static Tabs instance;

    public static Tabs getInstance(){
        if (instance == null){
            instance = new Tabs();
        }
        return instance;
    }

    @FXML
    TabPane tabs;

    @FXML
    Tab themeTab;

    @FXML
    Tab mapTab;

    public Tabs() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/fr/univ_amu/iut/javaFX/windows/Tabs.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        themeTab.setContent(new ThemeScroll());
    }

    public void addATab(String tabTitle, Node content, boolean closeable) {
        Tab newTab = new Tab(tabTitle);
        newTab.setClosable(closeable);
        newTab.getStyleClass().add("tabs");
        newTab.setContent(content);
        tabs.getTabs().add(newTab);
    }

}
