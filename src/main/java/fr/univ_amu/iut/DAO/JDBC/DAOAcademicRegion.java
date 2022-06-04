package fr.univ_amu.iut.DAO.JDBC;

import fr.univ_amu.iut.DAO.entities.AcademicRegion;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.Objects;

public class DAOAcademicRegion implements fr.univ_amu.iut.DAO.DAOAcademicRegion {
    private final PreparedStatement findAllStatement;
    private final PreparedStatement getByIdStatement;
    private final PreparedStatement insertStatement;
    private final PreparedStatement getNextIdStatement;

    public DAOAcademicRegion(){
        findAllStatement = Database.prepare("SELECT * FROM academicRegion");
        getByIdStatement = Database.prepare("SELECT * FROM academicRegion WHERE idAcademicRegion = ?");
        insertStatement = Database.prepareInsert("INSERT INTO academicRegion (idAcademicRegion, nameAcademicRegion) VALUES (?, ?)");
        getNextIdStatement = Database.prepare("SELECT IdAcademicRegion FROM AcademicRegion WHERE IdAcademicRegion >=ALL (SELECT IdAcademicRegion FROM AcademicRegion)");
    }

    public static AcademicRegion extractAcademicRegion(ResultSet resultSet) throws SQLException {
        AcademicRegion academicRegion = new AcademicRegion();
        academicRegion.setId(resultSet.getInt("idAcademicRegion"));
        academicRegion.setName(resultSet.getString("nameAcademicRegion"));
        return academicRegion;
    }

    private void extractAcademicRegion(ResultSet resultSet, List<AcademicRegion> academicRegions) throws SQLException {
        while (resultSet.next()){
            academicRegions.add(extractAcademicRegion(resultSet));
        }
    }

    @Override
    public List<AcademicRegion> findAll() {
        List<AcademicRegion> academicRegions = new ArrayList<>();
        try {
            ResultSet resultSet = Objects.requireNonNull(findAllStatement).executeQuery();
            extractAcademicRegion(resultSet, academicRegions);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return academicRegions;
    }

    @Override
    public AcademicRegion getById(Integer id) {
        AcademicRegion academicRegion = new AcademicRegion();
        try {
            Objects.requireNonNull(getByIdStatement).setInt(1,id);
            ResultSet resultSet = getByIdStatement.executeQuery();
            if (resultSet.first()){
                academicRegion = extractAcademicRegion(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return academicRegion;
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
    public boolean insert(AcademicRegion object) {
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
