package fr.univ_amu.iut;

import fr.univ_amu.iut.dialogs.SettingsContent;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.ApplicationTest;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.util.NodeQueryUtils.hasText;

@ExtendWith(ApplicationExtension.class)
public class changeThemeLangTest extends ApplicationTest {
    @Test
    public void changethemelangtest() {
        clickOn("#buttonSettings");
        clickOn("#choiceBox");
        clickOn("Anglais");
        clickOn("#toggleButton");
        clickOn("Soumettre");
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        new ApplicationMain().start(primaryStage);
    }
}