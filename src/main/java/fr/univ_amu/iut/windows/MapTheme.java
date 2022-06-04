package fr.univ_amu.iut.windows;

import fr.univ_amu.iut.DAO.DAOThemeOfUse;
import fr.univ_amu.iut.DAO.entities.ThemeOfUse;
import fr.univ_amu.iut.DAO.factory.DAOFactoryProducer;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;
import java.util.List;

public class MapTheme extends GridPane {
    private List<ThemeOfUse> themeOfUseList;

    public MapTheme() {
        this.addThemes(this.getThemes());
    }

    public void addThemes(List<Theme> themeList) {
        int column = 0, row = 0;

        for (int i = 0; i < themeList.size(); i++, column++) {
            themeList.get(i).setPrefSize(150, 100);
            setMargin(themeList.get(i), new Insets(13));
            if (i != 0 && i%3==0) {
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
            themeList.add(new Theme(themeOfUse.getName()));
        }

        return themeList;
    }
}
