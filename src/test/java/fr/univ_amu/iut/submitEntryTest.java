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
    public void submitentrytest() {
        clickOn("#userNameField");
        write("admin");
        clickOn("#passwordField");
        write("admin");
        clickOn("#submitButton");
        clickOn("Saisie");
        // Le clic pour selectionner cet valeur ne marche PAS!!! souvent, mais pas toujour
        clickOn("#choiceBoxThemeOfUse");
        clickOn("TNE");
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
        clickOn("#fieldIdOfActorFirstName");
        write("Alexandre");
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
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        new ApplicationMain().start(primaryStage);
    }
}