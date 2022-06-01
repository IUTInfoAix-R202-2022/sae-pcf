package fr.univ_amu.iut.DAO;

import java.sql.*;
import java.sql.SQLException;

public class ConnectionManager {
    static private final String URL = "jdbc:postgresql://rogue.db.elephantsql.com/zizafbet";
    static private final String LOGIN = "zizfbet";
    static private final String PASSWORD = "UJSPDbZ3RxceJ1mpEwRtfPCeTqnXmnYx";
    private Connection connection;

    public ConnectionManager(){
        try {
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection(){
        return this.connection;
    }
}
