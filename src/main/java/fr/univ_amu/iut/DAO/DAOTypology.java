package fr.univ_amu.iut.DAO;

import fr.univ_amu.iut.DAO.entities.Typology;

import java.util.List;

public interface DAOTypology extends DAO<Typology,Integer> {
    boolean delete(Typology object);

    boolean update(Typology object);

    int getNextId();
}
