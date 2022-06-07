package fr.univ_amu.iut.windows;

import fr.univ_amu.iut.DAO.DAOThemeOfUse;
import fr.univ_amu.iut.DAO.entities.ThemeOfUse;
import fr.univ_amu.iut.DAO.factory.DAOFactoryProducer;
import fr.univ_amu.iut.bundle.BundleManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextAlignment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ThemeVisualisation extends GridPane {
    private List<ThemeOfUse> themeOfUseList;

    public ThemeVisualisation() {
        this.setAlignment(Pos.TOP_CENTER);
        this.getStyleClass().add("transparentBG");
        //gridPane.setHgap(13);
        //gridPane.setVgap(13);
        addThemes(this.getThemes());
    }

    public void addThemes(List<Theme> themeList) {
        int column = 0, row = 0;
        
        for (int i = 0; i < themeList.size(); i++, column++) {
            themeList.get(i).setPrefSize(200, 150);
            setMargin(themeList.get(i), new Insets(13));
            if (i != 0 && i%5==0) {
                ++row;
                column = 0;
            }
            this.add(themeList.get(i), column, row);
        }
    }

    public List<Theme> getThemes() {
        DAOThemeOfUse daoThemeOfUse = DAOFactoryProducer.getFactory().createDaoThemeOfUse();
        this.themeOfUseList = daoThemeOfUse.findAll();

        List<Theme> themeList = new ArrayList<>();
        for (ThemeOfUse themeOfUse : themeOfUseList){
            themeList.add(new Theme(themeOfUse.getName(),themeOfUse.getId()));
        }

        return themeList;
    }
}
