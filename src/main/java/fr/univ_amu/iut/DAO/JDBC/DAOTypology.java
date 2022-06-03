package fr.univ_amu.iut.DAO.JDBC;

import fr.univ_amu.iut.DAO.entities.Typology;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DAOTypology implements fr.univ_amu.iut.DAO.DAOTypology {

    private final PreparedStatement findAllStatement;
    private final PreparedStatement getByIdStatement;
    private final PreparedStatement insertStatement;
    private final PreparedStatement updateStatement;
    private final PreparedStatement deleteStatement;

    public DAOTypology(){
        findAllStatement = Database.prepare("SELECT * FROM Typology");
        getByIdStatement = Database.prepare("SELECT * FROM Typology WHERE idTypology = ?");
        insertStatement = Database.prepareInsert("INSERT INTO Typology (idTypology, idTypology, name, firstName) VALUES (?, ?, ?, ?)");
        updateStatement = Database.prepare("UPDATE Typology SET idTypology = ?, idTypology = ?, name = ?, firstName = ?");
        deleteStatement = Database.prepare("DELETE FROM Typology WHERE idTypology = ?");
    }

    public static Typology extractTypology(ResultSet resultSet) throws SQLException {
        Typology Typology = new Typology();
        Typology.setId(resultSet.getInt("idTypology"));
        Typology.setIdThemeOfUse(resultSet.getInt("idThemeOfUse"));
        Typology.setIdDiscipline(resultSet.getInt("idDiscipline"));
        Typology.setIdDegree(resultSet.getInt("idDegree"));
        Typology.setIdAcademy(resultSet.getInt("idAcademy"));
        Typology.setIdAcademicRegion(resultSet.getInt("idAcademicRegion"));
        Typology.setActorType(resultSet.getString("actorType"));
        Typology.setLink(resultSet.getString("link"));
        Typology.setResourceName(resultSet.getString("resourceName"));
        Typology.setResourceType(resultSet.getString("resourceType"));
        Typology.setCommentary(resultSet.getString("commentary"));
        return Typology;
    }

    private void extractTypology(ResultSet resultSet, List<Typology> actorIdentities) throws SQLException {
        while (resultSet.next()){
            actorIdentities.add(extractTypology(resultSet));
        }
    }

    @Override
    public List<Typology> findAll() {
        List<Typology> actorIdentities = new ArrayList<>();
        try {
            ResultSet resultSet = Objects.requireNonNull(findAllStatement).executeQuery();
            extractTypology(resultSet, actorIdentities);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return actorIdentities;
    }

    @Override
    public Typology getById(Integer id) {
        Typology Typology = new Typology();
        try {
            Objects.requireNonNull(getByIdStatement).setInt(1,id);
            ResultSet resultSet = getByIdStatement.executeQuery();
            if (resultSet.first()){
                Typology = extractTypology(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Typology;
    }

    @Override
    public boolean insert(Typology object) {
        synchronized (Objects.requireNonNull(insertStatement)) {
            try {
                insertStatement.setInt(1,object.getId());
                insertStatement.setInt(2,object.getIdThemeOfUse());
                insertStatement.setInt(3,object.getIdDiscipline());
                insertStatement.setInt(4,object.getIdDegree());
                insertStatement.setInt(5,object.getIdAcademy());
                insertStatement.setInt(6,object.getIdAcademicRegion());

                insertStatement.setString(7,object.getActorType());
                insertStatement.setString(8,object.getLink());
                insertStatement.setString(9,object.getResourceName());
                insertStatement.setString(10,object.getResourceType());
                insertStatement.setString(11,object.getCommentary());

                insertStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean update(Typology object) {
        synchronized (Objects.requireNonNull(updateStatement)) {
            try {
                updateStatement.setInt(1,object.getId());
                updateStatement.setInt(2,object.getIdThemeOfUse());
                updateStatement.setInt(3,object.getIdDiscipline());
                updateStatement.setInt(4,object.getIdDegree());
                updateStatement.setInt(5,object.getIdAcademy());
                updateStatement.setInt(6,object.getIdAcademicRegion());

                updateStatement.setString(7,object.getActorType());
                updateStatement.setString(8,object.getLink());
                updateStatement.setString(9,object.getResourceName());
                updateStatement.setString(10,object.getResourceType());
                updateStatement.setString(11,object.getCommentary());

                updateStatement.executeUpdate();
            } catch (SQLException e) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean delete(Typology object) {
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
}
