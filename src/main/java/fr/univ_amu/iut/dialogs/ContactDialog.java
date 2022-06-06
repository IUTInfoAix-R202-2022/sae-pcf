package fr.univ_amu.iut.dialogs;

import fr.univ_amu.iut.bundle.Bundleable;
import javafx.scene.control.ButtonType;

import java.util.ResourceBundle;

public class ContactDialog extends SimpleDialog implements Bundleable {

    private String URL;

    private String header;
    private String content;
    public ContactDialog(String URL) {
        super();

        generateBundle();

        super.setHeader(header + " " + URL);
        super.setContent(content);
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

    @Override
    public void generateBundle(){
        super.generateBundle();

        ResourceBundle resourceBundle = ResourceBundle.getBundle("languages/dialogs/contactDialog");

        this.header = resourceBundle.getString("header");
        this.content = resourceBundle.getString("content");
    }
}
