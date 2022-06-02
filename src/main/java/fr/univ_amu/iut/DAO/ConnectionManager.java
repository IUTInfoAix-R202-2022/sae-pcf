package fr.univ_amu.iut.DAO;

import java.sql.*;
import java.sql.SQLException;

public class ConnectionManager {
    private static final String URL = "jdbc:postgresql://rogue.db.elephantsql.com/zizafbet";
    private static final String LOGIN = "zizafbet";
    private static final String PASSWORD = "UJSPDbZ3RxceJ1mpEwRtfPCeTqnXmnYx";
    private static ConnectionManager instance;
    private Connection connection;

    private ConnectionManager(){
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

    public void commit(){
        try {
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ConnectionManager getInstance(){
        if (instance == null){
            instance = new ConnectionManager();
            try {
                instance.connection = DriverManager.getConnection(URL,LOGIN,PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else{
            try {
                if (instance.connection.isClosed()){
                    instance.connection = DriverManager.getConnection(URL, LOGIN,PASSWORD);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }
}
