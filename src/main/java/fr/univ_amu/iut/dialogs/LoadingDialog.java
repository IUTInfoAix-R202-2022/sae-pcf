package fr.univ_amu.iut.dialogs;

import javafx.scene.control.Dialog;

public class LoadingDialog extends Dialog {

    private LoadingContent loadingDialogPane;

    public LoadingDialog(){
        loadingDialogPane = new LoadingContent();
        this.setDialogPane(loadingDialogPane);
    }

    public LoadingDialog(String loadingName){
        loadingDialogPane = new LoadingContent(loadingName);
        this.setDialogPane(loadingDialogPane);
    }

    public void addProgressToProgressBar(Double progress){

        this.loadingDialogPane.progressLoadingBar(progress);

        if (this.loadingDialogPane.getProgressBar().getProgress() >= 1){
            this.setResult(Boolean.TRUE);
            this.close();
        }
    }

    public void endLoad(){
        this.setResult(Boolean.TRUE);
        this.close();
    }

    public void cancelLoad(){
        this.setResult(Boolean.FALSE);
        this.close();
    }

    public void setLoadingDetails(String details){
        this.loadingDialogPane.setDetailsLabelText(details);
    }
}
