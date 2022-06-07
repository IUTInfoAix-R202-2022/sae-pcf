package fr.univ_amu.iut.DAO;

import java.util.List;

public interface DAO<Entity, Key> { // in this interface we declare all the methods that the JDBC classes will have
    List<Entity> findAll();

    Entity getById(Key id);

    int getNextId();

    boolean insert(Entity object);
}
