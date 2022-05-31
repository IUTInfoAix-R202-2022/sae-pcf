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
            //This part of the program came from this website : https://mkyong.com/java/open-browser-in-java-windows-or-linux/
            String os = System.getProperty("os.name").toLowerCase();
            Runtime rt = Runtime.getRuntime();
            try{
                if (os.indexOf( "win" ) >= 0) {
                    rt.exec( "rundll32 url.dll,FileProtocolHandler " + URL);

                } else if (os.indexOf( "mac" ) >= 0) {

                    rt.exec( "open " + URL);

                } else if (os.indexOf( "nix") >=0 || os.indexOf( "nux") >=0) {

                    // Do a best guess on unix until we get a platform independent way
                    // Build a list of browsers to try, in this order.
                    String[] browsers = {"epiphany", "firefox", "mozilla", "konqueror",
                            "netscape","opera","links","lynx"};

                    // Build a command string which looks like "browser1 "url" || browser2 "url" ||..."
                    StringBuffer cmd = new StringBuffer();
                    for (int i=0; i<browsers.length; i++)
                        cmd.append( (i==0  ? "" : " || " ) + browsers[i] +" \"" + URL + "\" ");

                    rt.exec(new String[] { "sh", "-c", cmd.toString() });

                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


}
