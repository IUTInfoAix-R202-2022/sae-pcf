package fr.univ_amu.iut.windows;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class Tabs extends Pane {

    @FXML
    TabPane tabs;

    void main(){

    }
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
    }

    public void addATab(String tabTitle, Node content, boolean closeable) {
        Tab newTab = new Tab(tabTitle);
        newTab.setClosable(closeable);
        newTab.setStyle("tabs");
        newTab.setContent(content);
        tabs.getTabs().add(newTab);
    }

}