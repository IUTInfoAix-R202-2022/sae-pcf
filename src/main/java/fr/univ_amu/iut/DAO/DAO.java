package fr.univ_amu.iut.DAO;

import java.util.List;

public interface DAO<Entity, Key> {
    List<Entity> findAll();

    Entity getById(Key id);

    int getNextId();

    boolean insert(Entity object);
}
