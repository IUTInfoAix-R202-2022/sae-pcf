package fr.univ_amu.iut.DAO.JDBC;

import fr.univ_amu.iut.DAO.entities.Academy;
import fr.univ_amu.iut.DAO.entities.ActorIdentity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DAOActorIdentity implements fr.univ_amu.iut.DAO.DAOActorIdentity {

    private final PreparedStatement findAllStatement;
    private final PreparedStatement getByIdStatement;
    private final PreparedStatement insertStatement;
    private final PreparedStatement updateStatement;
    private final PreparedStatement deleteStatement;
    private final PreparedStatement getNextIdStatement;
    private final PreparedStatement getByTypologyIdStatment;

    public DAOActorIdentity(){
        findAllStatement = Database.prepare("SELECT * FROM actorIdentity");
        getByIdStatement = Database.prepare("SELECT * FROM actorIdentity WHERE idActorIdentity = ?");
        insertStatement = Database.prepareInsert("INSERT INTO actorIdentity (idActorIdentity, idTypology, name) VALUES (?, ?, ?)");
        updateStatement = Database.prepare("UPDATE actorIdentity SET idTypology = ?, name = ?, firstName = ? WHERE idActorIdentity = ?");
        deleteStatement = Database.prepare("DELETE FROM actorIdentity WHERE idActorIdentity = ?");
        getNextIdStatement = Database.prepare("SELECT IdActorIdentity FROM ActorIdentity WHERE IdActorIdentity >=ALL (SELECT IdActorIdentity FROM ActorIdentity)");
        getByTypologyIdStatment = Database.prepare("SELECT * FROM actorIdentity WHERE idTypology = ?");
    }

    public static ActorIdentity extractActorIdentity(ResultSet resultSet) throws SQLException {
        ActorIdentity actorIdentity = new ActorIdentity();
        actorIdentity.setId(resultSet.getInt("idActorIdentity"));
        actorIdentity.setIdTypo(resultSet.getInt("idTypology"));
        actorIdentity.setName(resultSet.getString("name"));
        return actorIdentity;
    }

    private void extractActorIdentity(ResultSet resultSet, List<ActorIdentity> actorIdentities) throws SQLException {
        while (resultSet.next()){
            actorIdentities.add(extractActorIdentity(resultSet));
        }
    }

    @Override
    public List<ActorIdentity> findAll() {
        List<ActorIdentity> actorIdentities = new ArrayList<>();
        try {
            ResultSet resultSet = Objects.requireNonNull(findAllStatement).executeQuery();
            extractActorIdentity(resultSet, actorIdentities);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return actorIdentities;
    }

    @Override
    public ActorIdentity getById(Integer id) {
        ActorIdentity actorIdentity = new ActorIdentity();
        try {
            Objects.requireNonNull(getByIdStatement).setInt(1,id);
            ResultSet resultSet = getByIdStatement.executeQuery();
            if (resultSet.next()){
                actorIdentity = extractActorIdentity(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return actorIdentity;
    }

    @Override
    public boolean insert(ActorIdentity object) {
        synchronized (Objects.requireNonNull(insertStatement)) {
            try {
                insertStatement.setInt(1,object.getId());
                insertStatement.setInt(2,object.getIdTypo());
                insertStatement.setString(3,object.getName());
                insertStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean update(ActorIdentity object) {
        synchronized (Objects.requireNonNull(updateStatement)) {
            try {
                updateStatement.setInt(1,object.getIdTypo());
                updateStatement.setString(2,object.getName());
                updateStatement.setInt(3,object.getId());
                updateStatement.executeUpdate();
            } catch (SQLException e) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean delete(ActorIdentity object) {
        synchronized (Objects.requireNonNull(deleteStatement)) {
            try {
                deleteStatement.setInt(1, object.getId());
                deleteStatement.executeUpdate();
            } catch (SQLException e) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int getNextId() {
        try {
            ResultSet resultSet = Objects.requireNonNull(getNextIdStatement).executeQuery();
            resultSet.next();
            return resultSet.getInt(1)+1;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<ActorIdentity> getByTypologyId(int typologyId) {
        List<ActorIdentity> actorIdentities = new ArrayList<>();
        try {
            getByTypologyIdStatment.setInt(1, typologyId);
            ResultSet resultSet = Objects.requireNonNull(getByTypologyIdStatment).executeQuery();
            extractActorIdentity(resultSet, actorIdentities);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return actorIdentities;
    }
}
