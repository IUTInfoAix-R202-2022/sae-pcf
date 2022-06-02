package fr.univ_amu.iut.DAO.factory;

import fr.univ_amu.iut.DAO.JDBC.DAOAcademicRegion;

public interface DAOFactory {
    DAOAcademicRegion createDAOAcademicRegion();
}
