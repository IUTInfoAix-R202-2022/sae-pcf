package fr.univ_amu.iut.bundle;

import fr.univ_amu.iut.windows.Tabs;
import javafx.scene.Node;
import javafx.scene.Scene;

import java.util.ResourceBundle;

import javafx.scene.control.*;

public class BundleManager {
    private static Scene scene;

    public static void setup(Scene scene){
        BundleManager.scene = scene;
    }

    public static void updateBundle(){
        //MainWindow
        ResourceBundle updateBundleMainWindow = ResourceBundle.getBundle("languages/mainWindow");

        Button buttonSettings = (Button) BundleManager.scene.lookup("#buttonSettings");
        buttonSettings.setText(updateBundleMainWindow.getString("settings"));

        Button buttonContact = (Button)  BundleManager.scene.lookup("#buttonContact");
        buttonContact.setText(updateBundleMainWindow.getString("contact"));

        Button buttonHelp = (Button) BundleManager.scene.lookup("#buttonHelp");
        buttonHelp.setText(updateBundleMainWindow.getString("help"));

        //Home
        try {
            ResourceBundle resourceBundle = ResourceBundle.getBundle("languages/home");

            Button dataAccessButton = (Button) BundleManager.scene.lookup("#dataAccessButton");
            dataAccessButton.setText(resourceBundle.getString("accessToData"));

            Label labelConnectionAsAdministrator = (Label) BundleManager.scene.lookup("#labelConnectionAsAdministrator");
            labelConnectionAsAdministrator.setText(resourceBundle.getString("connectAsAdmin"));

            TextField userNameField = (TextField) BundleManager.scene.lookup("#userNameField");
            userNameField.setPromptText(resourceBundle.getString("username"));

            PasswordField passwordField = (PasswordField) BundleManager.scene.lookup("#passwordField");
            passwordField.setPromptText(resourceBundle.getString("password"));

            Button submitButton = (Button) BundleManager.scene.lookup("#submitButton");
            submitButton.setText(resourceBundle.getString("submit"));

            Label errorMessage = (Label) BundleManager.scene.lookup("#errorMessage");
            errorMessage.setText(resourceBundle.getString("errorMessage"));
        } catch (NullPointerException ignored){}

        //Tabs
        try {
            TabPane tabPane = (TabPane) BundleManager.scene.lookup("#tabs");
            ResourceBundle resourceBundle = ResourceBundle.getBundle("languages/tabs");
            for (Tab tab : tabPane.getTabs()){
                if (tab.getId().equals("themeTab")){
                    tab.setText(resourceBundle.getString("themes"));
                }
                else if (tab.getId().equals("mapTab")) {
                    tab.setText(resourceBundle.getString("map"));
                }
                else if (tab.getId().equals("dataEntry")) {
                    tab.setText(resourceBundle.getString("dataEntry"));
                }
            }
        }catch (NullPointerException ignored){}

        //DataEntry
        try{
            ResourceBundle resourceBundle = ResourceBundle.getBundle("languages/dataEntry");

            TabPane tabPane = (TabPane) BundleManager.scene.lookup("#tabs");
            for (Tab tab : tabPane.getTabs()){
                if (tab.getId().equals("dataEntry")) {
                    ((Label) tab.getContent().lookup("#allFieldsFill")).setText(resourceBundle.getString("allFieldsFill"));
                    ((Label) tab.getContent().lookup("#themeOfUseLabel")).setText(resourceBundle.getString("themeOfUse"));
                    ((Label) tab.getContent().lookup("#disciplineLabel")).setText(resourceBundle.getString("discipline"));
                    ((Label) tab.getContent().lookup("#degreeLabel")).setText(resourceBundle.getString("degree"));
                    ((Label) tab.getContent().lookup("#academyLabel")).setText(resourceBundle.getString("academy"));
                    ((Label) tab.getContent().lookup("#academicRegionLabel")).setText(resourceBundle.getString("academicRegion"));
                    ((Label) tab.getContent().lookup("#actorTypeLabel")).setText(resourceBundle.getString("actorType"));
                    ((Label) tab.getContent().lookup("#actorIdentityLabel")).setText(resourceBundle.getString("actorIdentity"));
                    ((TextField) tab.getContent().lookup("#fieldIdOfActorName")).setPromptText(resourceBundle.getString("name"));
                    ((Button) tab.getContent().lookup("#buttonAddIdOfActor")).setText(resourceBundle.getString("addAnActor"));
                    try {
                        ((Button) tab.getContent().lookup("#removeButton")).setText(resourceBundle.getString("deleteAnActor"));
                    }catch (NullPointerException ignored){}
                    ((Label) tab.getContent().lookup("#resourceNameLabel")).setText(resourceBundle.getString("resourceName"));
                    ((Label) tab.getContent().lookup("#resourceLinkLabel")).setText(resourceBundle.getString("resourceLink"));
                    ((Label) tab.getContent().lookup("#sourceTypeLabel")).setText(resourceBundle.getString("sourceType"));
                    ((Label) tab.getContent().lookup("#commentariesLabel")).setText(resourceBundle.getString("commentaries"));
                    ((Button) tab.getContent().lookup("#submitNewDataButton")).setText(resourceBundle.getString("submit"));
                }
            }
        }catch (NullPointerException ignored){}
    }

    private BundleManager(){}
}
