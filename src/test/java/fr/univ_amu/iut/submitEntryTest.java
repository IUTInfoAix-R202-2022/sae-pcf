package fr.univ_amu.iut;

import javafx.geometry.VerticalDirection;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.ApplicationTest;

import static org.testfx.api.FxAssert.verifyThat;

@ExtendWith(ApplicationExtension.class)
public class submitEntryTest extends ApplicationTest {
    @Test
    public void submitentrytest() throws InterruptedException {
        // Faire ce test en dehors des autres test !!!
        clickOn("#userNameField");
        write("admin");
        clickOn("#passwordField");
        write("admin");
        clickOn("#submitButton");
        clickOn("Saisi");
        clickOn("#choiceBoxThemeOfUse");
        clickOn("Tiers lab");
        clickOn("#choiceBoxDiscipline");
        clickOn("EPS");
        clickOn("#choiceBoxDegree");
        clickOn("Premier degré");
        clickOn("#choiceBoxAcademy");
        clickOn("Académie d Aix-Marseille");
        scroll(VerticalDirection.DOWN);
        scroll(VerticalDirection.DOWN);
        scroll(VerticalDirection.DOWN);
        scroll(VerticalDirection.DOWN);
        scroll(VerticalDirection.DOWN);
        scroll(VerticalDirection.DOWN);
        scroll(VerticalDirection.DOWN);
        scroll(VerticalDirection.DOWN);
        scroll(VerticalDirection.DOWN);
        scroll(VerticalDirection.DOWN);
        scroll(VerticalDirection.DOWN);
        scroll(VerticalDirection.DOWN);
        scroll(VerticalDirection.DOWN);
        scroll(VerticalDirection.DOWN);
        scroll(VerticalDirection.DOWN);
        clickOn("#fieldTypeOfActors");
        write("Test");
        clickOn("#fieldIdOfActorName");
        write("Ganassi");
        clickOn("#fieldResourceLink");
        write("Test");
        scroll(VerticalDirection.DOWN);
        scroll(VerticalDirection.DOWN);
        scroll(VerticalDirection.DOWN);
        scroll(VerticalDirection.DOWN);
        scroll(VerticalDirection.DOWN);
        scroll(VerticalDirection.DOWN);
        scroll(VerticalDirection.DOWN);
        scroll(VerticalDirection.DOWN);
        scroll(VerticalDirection.DOWN);
        scroll(VerticalDirection.DOWN);
        scroll(VerticalDirection.DOWN);
        scroll(VerticalDirection.DOWN);
        scroll(VerticalDirection.DOWN);
        clickOn("#submitNewDataButton");
        clickOn("OK");
        clickOn("Thèmes");
        scroll(VerticalDirection.DOWN);
        scroll(VerticalDirection.DOWN);
        scroll(VerticalDirection.DOWN);
        scroll(VerticalDirection.DOWN);
        scroll(VerticalDirection.DOWN);
        clickOn("Tiers lab");
        sleep(1000);
        clickOn(1000, 180);
        clickOn("Modifier");
        clickOn("#choiceBoxAcademy");
        clickOn("Académie de Nice");
        clickOn(1260,760);
        clickOn("OK");
        clickOn(1000, 180);
        clickOn("Supprimer");
        clickOn("OK");
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        new ApplicationMain().start(primaryStage);
    }
}