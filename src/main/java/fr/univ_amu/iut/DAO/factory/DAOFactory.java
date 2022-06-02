package fr.univ_amu.iut.DAO.factory;

import fr.univ_amu.iut.DAO.DAODiscipline;
import fr.univ_amu.iut.DAO.JDBC.*;

public interface DAOFactory {
    DAOAcademicRegion createDAOAcademicRegion();
    DAOAcademy createDAOAcademy();
    DAOActorIdentity createDaoActorIdentity();
    DAODegree createDaoDegree();
    DAODiscipline createDiscipline();
    DAOThemeOfUse createDaoThemeOfUse();
    DAOTypology createDaoTypology();
}
