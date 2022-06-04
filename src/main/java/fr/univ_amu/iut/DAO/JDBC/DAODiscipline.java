package fr.univ_amu.iut.DAO.JDBC;

import fr.univ_amu.iut.DAO.entities.Discipline;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.Objects;

public class DAODiscipline implements fr.univ_amu.iut.DAO.DAODiscipline {
    private final PreparedStatement findAllStatement;
    private final PreparedStatement getByIdStatement;
    private final PreparedStatement insertStatement;
    private final PreparedStatement getNextIdStatement;

    public DAODiscipline(){
        findAllStatement = Database.prepare("SELECT * FROM Discipline");
        getByIdStatement = Database.prepare("SELECT * FROM Discipline WHERE idDiscipline = ?");
        insertStatement = Database.prepareInsert("INSERT INTO Discipline (idDiscipline, nameDiscipline) VALUES (?, ?)");
        getNextIdStatement = Database.prepare("SELECT IdDiscipline FROM Discipline WHERE IdDiscipline >=ALL (SELECT IdDiscipline FROM Discipline)");
    }

    public static Discipline extractDiscipline(ResultSet resultSet) throws SQLException {
        Discipline Discipline = new Discipline();
        Discipline.setId(resultSet.getInt("idDiscipline"));
        Discipline.setName(resultSet.getString("nameDiscipline"));
        return Discipline;
    }

    private void extractDiscipline(ResultSet resultSet, List<Discipline> Disciplines) throws SQLException {
        while (resultSet.next()){
            Disciplines.add(extractDiscipline(resultSet));
        }
    }

    @Override
    public List<Discipline> findAll() {
        List<Discipline> Disciplines = new ArrayList<>();
        try {
            ResultSet resultSet = Objects.requireNonNull(findAllStatement).executeQuery();
            extractDiscipline(resultSet, Disciplines);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Disciplines;
    }

    @Override
    public Discipline getById(Integer id) {
        Discipline Discipline = new Discipline();
        try {
            Objects.requireNonNull(getByIdStatement).setInt(1,id);
            ResultSet resultSet = getByIdStatement.executeQuery();
            if (resultSet.next()){
                Discipline = extractDiscipline(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Discipline;
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
    public boolean insert(Discipline object) {
        synchronized (Objects.requireNonNull(insertStatement)) {
            try {
                insertStatement.setInt(1,object.getId());
                insertStatement.setString(2,object.getName());
                insertStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }
}
