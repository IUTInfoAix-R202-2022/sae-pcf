package fr.univ_amu.iut.DAO.entities;

import java.util.List;
import java.util.Objects;

public class ThemeOfUse{

    private int id;
    private String name;

    public static ThemeOfUse findByName(List<ThemeOfUse> themeOfUseList, String name){
        for (ThemeOfUse themeOfUse : themeOfUseList){
            if (Objects.equals(themeOfUse.getName(), name)){
                return themeOfUse;
            }
        }
        return null;
    }

    public ThemeOfUse(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
