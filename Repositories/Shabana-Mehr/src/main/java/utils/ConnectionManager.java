package utils;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static Connection connection;
    private ConnectionManager(){
    }
    public static Connection getConnection() throws SQLException, IOException {
        if(connection == null){
            connection = connect();
        }
        return connection;
    }
    private static Connection connect() throws IOException, SQLException {

        connection = DriverManager.getConnection(null);
        return connection;
    }
}
