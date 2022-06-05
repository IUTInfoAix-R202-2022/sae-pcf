package fr.univ_amu.iut.DAO.JDBC;

import fr.univ_amu.iut.DAO.entities.Academy;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.Objects;

public class DAOAcademy implements fr.univ_amu.iut.DAO.DAOAcademy {
    private final PreparedStatement findAllStatement;
    private final PreparedStatement getByIdStatement;
    private final PreparedStatement insertStatement;
    private final PreparedStatement getNextIdStatement;

    public DAOAcademy(){
        findAllStatement = Database.prepare("SELECT * FROM academy");
        getByIdStatement = Database.prepare("SELECT * FROM academy WHERE idAcademy = ?");
        insertStatement = Database.prepareInsert("INSERT INTO academy (idAcademy, nameAcademy) VALUES (?, ?)");
        getNextIdStatement = Database.prepare("SELECT IdAcademy FROM Academy WHERE IdAcademy >=ALL (SELECT IdAcademy FROM Academy)");
    }

    public static Academy extractAcademy(ResultSet resultSet) throws SQLException {
        Academy academy = new Academy();
        academy.setId(resultSet.getInt("idAcademy"));
        academy.setName(resultSet.getString("nameAcademy"));
        return academy;
    }

    private void extractAcademy(ResultSet resultSet, List<Academy> academys) throws SQLException {
        while (resultSet.next()){
            academys.add(extractAcademy(resultSet));
        }
    }

    @Override
    public List<Academy> findAll() {
        List<Academy> academys = new ArrayList<>();
        try {
            ResultSet resultSet = Objects.requireNonNull(findAllStatement).executeQuery();
            extractAcademy(resultSet, academys);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return academys;
    }

    @Override
    public Academy getById(Integer id) {
        Academy academy = new Academy();
        try {
            Objects.requireNonNull(getByIdStatement).setInt(1,id);
            ResultSet resultSet = getByIdStatement.executeQuery();
            if (resultSet.next()){
                academy = extractAcademy(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return academy;
    }

    @Override
    public boolean insert(Academy object) {
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
}
