package fr.univ_amu.iut.DAO;

import fr.univ_amu.iut.DAO.entities.ActorIdentity;

import java.util.List;

public interface DAOActorIdentity extends DAO<ActorIdentity, Integer>{
    boolean update(ActorIdentity object);

    boolean delete(ActorIdentity object);

    int getNextId();

    List<ActorIdentity> getByTypologyId(int typologyId);
}
