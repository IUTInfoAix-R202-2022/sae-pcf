package fr.univ_amu.iut.dialogs;

import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.text.Font;


public class AddDataChoiceBoxDialog extends Dialog {

    private AddDataChoiceBoxContent dialogPane;
    private Button submitButton = new Button("Soumettre");

    public AddDataChoiceBoxDialog(String dataName){
        dialogPane = new AddDataChoiceBoxContent(dataName);
        this.setDialogPane(dialogPane);

        this.submitButton.setFont(Font.font("JetBrains Mono Regular"));
        this.submitButton.setOnAction(actionEvent -> {
            String text = dialogPane.getTextField().getText();

            if (text.strip().equals("")){
                dialogPane.getErrorMessageLabel().setVisible(true);
            }
            else{
                dialogPane.getErrorMessageLabel().setVisible(false);

                this.setResult(Boolean.TRUE);
                this.close();
            }
        });

        this.dialogPane.getVBox().getChildren().add(submitButton);
    }

}
