package fr.univ_amu.iut.DAO.factory;

public class DAOFactoryProducer {
    public static DAOFactory getFactory(){
        return new DAOFactoryJDBC();
    }
}
