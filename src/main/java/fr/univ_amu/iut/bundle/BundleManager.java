package fr.univ_amu.iut.bundle;

import fr.univ_amu.iut.windows.Tabs;
import javafx.scene.Node;
import javafx.scene.Scene;

import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.scene.control.*;

public class BundleManager {
    private static Scene scene;

    public static void setup(Scene scene){
        BundleManager.scene = scene;
    }

    public static void updateBundle(){
        //MainWindow
        try {
            ResourceBundle updateBundleMainWindow = ResourceBundle.getBundle("languages/mainWindow");

            Button buttonSettings = (Button) BundleManager.scene.lookup("#buttonSettings");
            buttonSettings.setText(updateBundleMainWindow.getString("settings"));

            Button buttonContact = (Button) BundleManager.scene.lookup("#buttonContact");
            buttonContact.setText(updateBundleMainWindow.getString("contact"));

            Button buttonHelp = (Button) BundleManager.scene.lookup("#buttonHelp");
            buttonHelp.setText(updateBundleMainWindow.getString("help"));
        }catch (NullPointerException ignored){}

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
                    ((Button) tab.getContent().lookup("#removeButton")).setText(resourceBundle.getString("deleteAnActor"));
                    ((Label) tab.getContent().lookup("#resourceNameLabel")).setText(resourceBundle.getString("resourceName"));
                    ((Label) tab.getContent().lookup("#resourceLinkLabel")).setText(resourceBundle.getString("resourceLink"));
                    ((Label) tab.getContent().lookup("#sourceTypeLabel")).setText(resourceBundle.getString("sourceType"));
                    ((Label) tab.getContent().lookup("#commentariesLabel")).setText(resourceBundle.getString("commentaries"));
                    ((Button) tab.getContent().lookup("#submitNewDataButton")).setText(resourceBundle.getString("submit"));
                }
            }
        }catch (NullPointerException ignored){}

        try{
            ResourceBundle resourceBundle = ResourceBundle.getBundle("languages/results");

            ((Label) BundleManager.scene.lookup("#entitledLabel")).setText(resourceBundle.getString("entitled"));
            ((Label) BundleManager.scene.lookup("#useLabel")).setText(resourceBundle.getString("use"));
            ((Label) BundleManager.scene.lookup("#disciplineLabel")).setText(resourceBundle.getString("discipline"));
            ((Label) BundleManager.scene.lookup("#resourceType")).setText(resourceBundle.getString("resourceType"));

            try {
                MenuButton menuButton = (MenuButton) BundleManager.scene.lookup("#adminButton");
                List<MenuItem> menuItems = menuButton.getItems();
                for (MenuItem menuItem : menuItems){
                    if (menuItem.getId().equals("modifyMenuItem")){
                        menuItem.setText(resourceBundle.getString("modify"));
                    }
                    else if (menuItem.getId().equals("deleteMenuItem")){
                        menuItem.setText(resourceBundle.getString("delete"));
                    }
                }
            }catch (NullPointerException ignored){}

        }catch (NullPointerException ignored){}

        //DetailedResult
        try{
            ResourceBundle resourceBundle = ResourceBundle.getBundle("languages/detailedResult");

            TabPane tabPane = (TabPane) BundleManager.scene.lookup("#tabs");
            for (Tab tab : tabPane.getTabs()){
                Set<Node> set = tab.getContent().lookupAll("#columnLabel");
                int index = 0;
                for (Node node : set) {
                    switch(index){
                        case 0:
                            ((Label) node).setText(resourceBundle.getString("themeOfUse"));
                            break;
                        case 1:
                            ((Label) node).setText(resourceBundle.getString("discipline"));
                            break;
                        case 2:
                            ((Label) node).setText(resourceBundle.getString("degree"));
                            break;
                        case 3:
                            ((Label) node).setText(resourceBundle.getString("academy"));
                            break;
                        case 4:
                            ((Label) node).setText(resourceBundle.getString("academicRegion"));
                            break;
                        case 5:
                            ((Label) node).setText(resourceBundle.getString("actorType"));
                            break;
                        case 6:
                            ((Label) node).setText(resourceBundle.getString("resourceLink"));
                            break;
                        case 7:
                            ((Label) node).setText(resourceBundle.getString("resourceName"));
                            break;
                        case 8:
                            ((Label) node).setText(resourceBundle.getString("sourceType"));
                            break;
                        case 9:
                            ((Label) node).setText(resourceBundle.getString("commentaries"));
                            break;
                        case 10:
                            ((Label) node).setText(resourceBundle.getString("actorIdentity"));
                            break;
                    }

                    if (index == 10){
                        index = 0;
                    }
                    else{
                        ++index;
                    }
                }
            }

            Set<Node> modifyButtons = tabPane.lookupAll("#modifyButton");
            for (Node n : modifyButtons){
                ((Button) n).setText(resourceBundle.getString("modify"));
            }
            Set<Node> deleteButtons = tabPane.lookupAll("#deleteButton");
            for (Node n : deleteButtons){
                ((Button) n).setText(resourceBundle.getString("delete"));
            }
            Set<Node> linkButtons = tabPane.lookupAll("#linkButton");
            for (Node n : linkButtons){
                ((Button) n).setText(resourceBundle.getString("linkButton"));
            }

        }catch (NullPointerException ignored){}
    }

    private BundleManager(){}
}
