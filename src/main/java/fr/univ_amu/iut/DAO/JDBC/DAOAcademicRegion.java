package fr.univ_amu.iut.DAO.JDBC;

import fr.univ_amu.iut.DAO.entities.AcademicRegion;

import java.sql.PreparedStatement;
import java.util.List;
import java.sql.*;

public class DAOAcademicRegion implements fr.univ_amu.iut.DAO.DAOAcademicRegion {
    //private final PreparedStatement findAllStatement;
    //private final PreparedStatement getByIdStatement;
    //private final PreparedStatement insertStatement;

    public DAOAcademicRegion(){
        //findAllStatement;
    }

    @Override
    public List<AcademicRegion> findAll() {
        return null;
    }

    @Override
    public AcademicRegion getById(Integer id) {
        return null;
    }

    @Override
    public boolean insert(AcademicRegion object) {
        return false;
    }
}
