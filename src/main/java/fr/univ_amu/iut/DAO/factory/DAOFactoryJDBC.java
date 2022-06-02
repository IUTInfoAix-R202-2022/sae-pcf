package fr.univ_amu.iut.DAO.factory;

import fr.univ_amu.iut.DAO.JDBC.DAOAcademicRegion;
import fr.univ_amu.iut.DAO.JDBC.Database;

public class DAOFactoryJDBC implements DAOFactory{
    static{
        Database.connect();
    }

    @Override
    public DAOAcademicRegion createDAOAcademicRegion(){
        return new DAOAcademicRegion();
    }
}
