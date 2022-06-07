package fr.univ_amu.iut.dialogs;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class LoadingContent extends DialogPane {

    @FXML
    private Label titleLabel;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Label progressBarLabel;

    @FXML
    private Label detailsLabel;

    @FXML
    private VBox vBox;

    public LoadingContent(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/fr/univ_amu/iut/javaFX/dialogs/Loading.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public LoadingContent(String loadingName){
        this();
        titleLabel.setText(titleLabel.getText()+" du "+loadingName);
        progressBarLabel.setText(progressBarLabel.getText()+" du "+loadingName);
    }

    public void setDetailsLabelText(String text) {        //To explain what is loading
        this.detailsLabel.setText(text);
    }

    public void progressLoadingBar(double progress){
        this.progressBar.setProgress(this.progressBar.getProgress() + progress);
    }

    public ProgressBar getProgressBar() {
        return this.progressBar;
    }

    public VBox getvBox() {
        return vBox;
    }
}
