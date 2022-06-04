package fr.univ_amu.iut.DAO.JDBC;

import fr.univ_amu.iut.DAO.entities.ThemeOfUse;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.Objects;

public class DAOThemeOfUse implements fr.univ_amu.iut.DAO.DAOThemeOfUse {
    private final PreparedStatement findAllStatement;
    private final PreparedStatement getByIdStatement;
    private final PreparedStatement insertStatement;

    public DAOThemeOfUse(){
        findAllStatement = Database.prepare("SELECT * FROM ThemeOfUse");
        getByIdStatement = Database.prepare("SELECT * FROM ThemeOfUse WHERE idThemeOfUse = ?");
        insertStatement = Database.prepareInsert("INSERT INTO ThemeOfUse (idThemeOfUse, nameThemeOfUse) VALUES (?, ?)");
    }

    public static ThemeOfUse extractThemeOfUse(ResultSet resultSet) throws SQLException {
        ThemeOfUse ThemeOfUse = new ThemeOfUse();
        ThemeOfUse.setId(resultSet.getInt("idThemeOfUse"));
        ThemeOfUse.setName(resultSet.getString("nameThemeOfUse"));
        return ThemeOfUse;
    }

    private void extractThemeOfUse(ResultSet resultSet, List<ThemeOfUse> ThemeOfUses) throws SQLException {
        while (resultSet.next()){
            ThemeOfUses.add(extractThemeOfUse(resultSet));
        }
    }

    @Override
    public List<ThemeOfUse> findAll() {
        List<ThemeOfUse> ThemeOfUses = new ArrayList<>();
        try {
            ResultSet resultSet = Objects.requireNonNull(findAllStatement).executeQuery();
            extractThemeOfUse(resultSet, ThemeOfUses);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ThemeOfUses;
    }

    @Override
    public ThemeOfUse getById(Integer id) {
        ThemeOfUse ThemeOfUse = new ThemeOfUse();
        try {
            Objects.requireNonNull(getByIdStatement).setInt(1,id);
            ResultSet resultSet = getByIdStatement.executeQuery();
            if (resultSet.next()){
                ThemeOfUse = extractThemeOfUse(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ThemeOfUse;
    }

    @Override
    public boolean insert(ThemeOfUse object) {
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
