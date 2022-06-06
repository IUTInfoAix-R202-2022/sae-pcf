package fr.univ_amu.iut.DAO;

import fr.univ_amu.iut.DAO.entities.ThemeOfUse;

public interface DAOThemeOfUse extends DAO<ThemeOfUse, Integer>{

    public ThemeOfUse getByName(String name);
}
