package fr.univ_amu.iut;

import javafx.geometry.VerticalDirection;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.ApplicationTest;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.util.NodeQueryUtils.hasText;

@ExtendWith(ApplicationExtension.class)
public class mapTest extends ApplicationTest {
    @Test
    public void cartetest() {
        clickOn("#userNameField");
        write("admin");
        clickOn("#passwordField");
        write("admin");
        clickOn("#submitButton");
        clickOn("#mapTab");
        clickOn(500, 600);
        scroll(VerticalDirection.DOWN);
        scroll(VerticalDirection.DOWN);
        scroll(VerticalDirection.DOWN);
        scroll(VerticalDirection.DOWN);
        scroll(VerticalDirection.DOWN);
        scroll(VerticalDirection.DOWN);
        scroll(VerticalDirection.DOWN);
        clickOn(700, 500);
        clickOn("Ludification");
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        new ApplicationMain().start(primaryStage);
    }
}