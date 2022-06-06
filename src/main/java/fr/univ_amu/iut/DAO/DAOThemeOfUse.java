package fr.univ_amu.iut.DAO;

import fr.univ_amu.iut.DAO.entities.Academy;
import fr.univ_amu.iut.DAO.entities.ThemeOfUse;

import java.util.List;

public interface DAOThemeOfUse extends DAO<ThemeOfUse, Integer>{

    public ThemeOfUse getByName(String name);

    public List<ThemeOfUse> getByAcademy(Academy academy);
}
