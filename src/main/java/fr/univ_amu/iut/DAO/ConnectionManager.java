package fr.univ_amu.iut.DAO;

import java.sql.*;
import java.sql.SQLException;

public class ConnectionManager { // This file is used to initialise connection with the database
    private static final String URL = "jdbc:postgresql://rogue.db.elephantsql.com/zizafbet";
    private static final String LOGIN = "zizafbet";
    private static final String PASSWORD = "UJSPDbZ3RxceJ1mpEwRtfPCeTqnXmnYx";
    private static ConnectionManager instance;  // There is just one connection with database
    private Connection connection;

    private ConnectionManager(){
    }

    public void closeConnection(){ // Method called at the close of the application to correctly close the connection with database
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

    public static ConnectionManager getInstance(){ // return the actual ConnectionManager or create it if it's not exist
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
