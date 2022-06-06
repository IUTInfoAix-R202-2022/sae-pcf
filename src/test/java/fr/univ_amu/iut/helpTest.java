package fr.univ_amu.iut;

import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.ApplicationTest;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.util.NodeQueryUtils.hasText;

@ExtendWith(ApplicationExtension.class)
public class helpTest extends ApplicationTest {
    @Test
    public void aidetest() {
        verifyThat("#buttonHelp", hasText("Aide"));
        clickOn("#buttonHelp");
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        new ApplicationMain().start(primaryStage);
    }
}