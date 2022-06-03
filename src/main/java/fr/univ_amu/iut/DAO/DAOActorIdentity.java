package fr.univ_amu.iut.DAO;

import fr.univ_amu.iut.DAO.entities.ActorIdentity;

public interface DAOActorIdentity extends DAO<ActorIdentity, Integer>{
    boolean update(ActorIdentity object);

    boolean delete(ActorIdentity object);

    int getNextId();
}
