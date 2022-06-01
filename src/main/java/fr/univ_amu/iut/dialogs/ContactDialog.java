package fr.univ_amu.iut.dialogs;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class ContactDialog extends SimpleDialog{

    private String URL;
    public ContactDialog(String URL) {
        super("Vous allez être redirigé vers le site : " + URL, "Si vous confirmez cette action, votre navigateur va s'ouvrir, nous ne pouvons pas sécuriser cette action");
        this.URL = URL;
    }

    @Override
    public void show(){
        result = dialog.showAndWait();
        if (result.get() == ButtonType.OK){
            try {
                Desktop.getDesktop().browse(new URI(this.URL));
            } catch (IOException | URISyntaxException e) {
                throw new RuntimeException(e);
            }
        }
    }


}
