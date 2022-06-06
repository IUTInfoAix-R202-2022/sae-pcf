package fr.univ_amu.iut.windows;

import fr.univ_amu.iut.dialogs.LoadingDialog;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class Tabs extends Pane {

    private static Tabs instance;
    private LoadingDialog loadingDialog;

    public static Tabs getInstance() {
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

    private Tabs() {
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
        mapTab.setContent(new MapTab());
    }

    public void addATab(String tabTitle, Node content, boolean closeable) {
        Tab newTab = new Tab(tabTitle);
        newTab.setClosable(closeable);
        newTab.getStyleClass().add("tabs");
        newTab.setContent(content);
        tabs.getTabs().add(newTab);
        tabs.getSelectionModel().select(newTab);
    }

    public void remove(int index){
        tabs.getTabs().remove(index);
    }

    public int getTabPaneSize(){
        return tabs.getTabs().size();
    }

    public void showLoading(String name){
        this.loadingDialog = new LoadingDialog(name);
        this.loadingDialog.show();
    }

    public LoadingDialog getLoadingDialog(){
        return this.loadingDialog;
    }


}
