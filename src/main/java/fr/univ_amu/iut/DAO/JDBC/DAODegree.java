package fr.univ_amu.iut.DAO.JDBC;

import fr.univ_amu.iut.DAO.entities.Degree;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.Objects;

public class DAODegree implements fr.univ_amu.iut.DAO.DAODegree {
    private final PreparedStatement findAllStatement;
    private final PreparedStatement getByIdStatement;
    private final PreparedStatement insertStatement;

    public DAODegree(){
        findAllStatement = Database.prepare("SELECT * FROM Degree");
        getByIdStatement = Database.prepare("SELECT * FROM Degree WHERE idDegree = ?");
        insertStatement = Database.prepareInsert("INSERT INTO Degree (idDegree, nameDegree) VALUES (?, ?)");
    }

    public static Degree extractDegree(ResultSet resultSet) throws SQLException {
        Degree Degree = new Degree();
        Degree.setId(resultSet.getInt("idDegree"));
        Degree.setName(resultSet.getString("nameDegree"));
        return Degree;
    }

    private void extractDegree(ResultSet resultSet, List<Degree> Degrees) throws SQLException {
        while (resultSet.next()){
            Degrees.add(extractDegree(resultSet));
        }
    }

    @Override
    public List<Degree> findAll() {
        List<Degree> Degrees = new ArrayList<>();
        try {
            ResultSet resultSet = Objects.requireNonNull(findAllStatement).executeQuery();
            extractDegree(resultSet, Degrees);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Degrees;
    }

    @Override
    public Degree getById(Integer id) {
        Degree Degree = new Degree();
        try {
            Objects.requireNonNull(getByIdStatement).setInt(1,id);
            ResultSet resultSet = getByIdStatement.executeQuery();
            if (resultSet.next()){
                Degree = extractDegree(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Degree;
    }

    @Override
    public boolean insert(Degree object) {
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
