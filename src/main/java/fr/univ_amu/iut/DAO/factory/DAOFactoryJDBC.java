package fr.univ_amu.iut.DAO.factory;

import fr.univ_amu.iut.DAO.JDBC.*;

public class DAOFactoryJDBC implements DAOFactory{
    static{
        Database.connect();
    }

    @Override
    public DAOAcademicRegion createDAOAcademicRegion(){
        return new DAOAcademicRegion();
    }

    @Override
    public DAOAcademy createDAOAcademy() {
        return new DAOAcademy();
    }

    @Override
    public DAOActorIdentity createDaoActorIdentity() {
        return new DAOActorIdentity();
    }

    @Override
    public DAODegree createDaoDegree() {
        return new DAODegree();
    }

    @Override
    public DAODiscipline createDAODiscipline(){
        return new DAODiscipline();
    }

    @Override
    public DAOThemeOfUse createDaoThemeOfUse() {
        return new DAOThemeOfUse();
    }

    @Override
    public DAOTypology createDaoTypology() {
        return new DAOTypology();
    }

}
