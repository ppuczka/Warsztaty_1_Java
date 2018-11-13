package util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbManager {

    private static final String DB_NAME = "workshop";
    private static final String URL = "jdbc:mysql://localhost:3306/"+DB_NAME+"?useSSL=false";
    private static final String USER = "root";
    private static final String PASS = "coderslab";

    private static DbManager INSTANCE;
    private Connection connection;
    private DbManager(){ }

    public static DbManager getInstance(){
        if(INSTANCE==null){
            INSTANCE = new DbManager();
        }
        return INSTANCE;
    }
    public Connection getConnection() throws SQLException {
        if(connection==null) {
            connection = DriverManager.getConnection(URL, USER, PASS);
        }
        return connection;
    }
    public void close() throws SQLException{
        connection.close();
    }
}