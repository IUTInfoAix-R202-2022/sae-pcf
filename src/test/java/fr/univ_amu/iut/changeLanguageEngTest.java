package fr.univ_amu.iut;

import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.ApplicationTest;

import static org.testfx.api.FxAssert.verifyThat;

@ExtendWith(ApplicationExtension.class)
public class changeLanguageEngTest extends ApplicationTest {
    @Test
    public void changelanguageengtest() {
        clickOn("#buttonSettings");
        clickOn("#choiceBox");
        clickOn("English");
        clickOn(800, 370);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        new ApplicationMain().start(primaryStage);
    }
}