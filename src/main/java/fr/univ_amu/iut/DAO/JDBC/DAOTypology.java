package fr.univ_amu.iut.DAO.JDBC;

import fr.univ_amu.iut.DAO.entities.ThemeOfUse;
import fr.univ_amu.iut.DAO.entities.Typology;
import javafx.scene.chart.PieChart;

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
    private final PreparedStatement getNextIdStatement;
    private final PreparedStatement getByThemeStatement;

    public DAOTypology(){
        findAllStatement = Database.prepare("SELECT * FROM Typology");
        getByIdStatement = Database.prepare("SELECT * FROM Typology WHERE idTypology = ?");
        insertStatement = Database.prepareInsert("INSERT INTO Typology (idTypology, idThemeOfUse, idDiscipline, idDegree, idAcademy, idAcademicRegion, actorType, link, resourceName, resourceType, commentary) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        updateStatement = Database.prepare("UPDATE Typology SET idThemeOfUse = ?, idDiscipline = ?, idDegree = ?, idAcademy = ?, idAcademicRegion = ?, actorType = ?, link = ?, resourceName = ?, resourceType = ?, commentary = ? WHERE idTypology = ?");
        deleteStatement = Database.prepare("DELETE FROM Typology WHERE idTypology = ?");
        getNextIdStatement = Database.prepare("SELECT IdTypology FROM Typology WHERE IdTypology >=ALL (SELECT IdTypology FROM Typology)");
        getByThemeStatement = Database.prepare("SELECT * FROM Typology WHERE IdThemeOfUse = ?");
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

    private void extractTypology(ResultSet resultSet, List<Typology> typologies) throws SQLException {
        while (resultSet.next()){
            typologies.add(extractTypology(resultSet));
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
            if (resultSet.next()){
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
                updateStatement.setInt(1,object.getIdThemeOfUse());
                updateStatement.setInt(2,object.getIdDiscipline());
                updateStatement.setInt(3,object.getIdDegree());
                updateStatement.setInt(4,object.getIdAcademy());
                updateStatement.setInt(5,object.getIdAcademicRegion());

                updateStatement.setString(6,object.getActorType());
                updateStatement.setString(7,object.getLink());
                updateStatement.setString(8,object.getResourceName());
                updateStatement.setString(9,object.getResourceType());
                updateStatement.setString(10,object.getCommentary());

                updateStatement.setInt(11,object.getId()); //for the WHERE

                updateStatement.executeUpdate();
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
    public List<Typology> findByThemeId(int themeId) {
        List<Typology> typologies = new ArrayList<>();
        synchronized (Objects.requireNonNull(getByThemeStatement)){
            try {
                getByThemeStatement.setInt(1,themeId);
                ResultSet resultSet = getByThemeStatement.executeQuery();
                extractTypology(resultSet,typologies);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return typologies;
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
