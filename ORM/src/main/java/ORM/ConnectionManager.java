package ORM;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionManager {
    public static ConnectionManager connectionManager;
    public static Connection connection;


    private ConnectionManager(){

    }

    private ConnectionManager connectionManager(){
        if(connectionManager == null){
            connectionManager = new ConnectionManager();
        }
        return connectionManager;
    }


    public static Connection getConnection(){
        if(connection == null){
            connection = connect();
        }
        return connection;
    }

    public static Connection connect(){
        try {
            Properties props = new Properties();
//            FileReader fr = new FileReader("src/main/resources/jdbc.properties");
//            props.load(fr);

            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            InputStream input = loader.getResourceAsStream("jdbc.properties");
            props.load(input);



//            jdbc:mariadb://hostname:port/databaseName?user=username&password=password
            String connectionURL = "jdbc:mariadb://" +
                    props.get("hostname") + ":" +
                    props.get("port") + "/" +
                    props.get("dbname") + "?user=" +
                    props.get("username") + "&password=" +
                    props.get("password");
         Class.forName("org.mariadb.jdbc.Driver");
            connection = DriverManager.getConnection(connectionURL);
//            System.out.println(connectionURL);
        } catch (Exception e){
            e.printStackTrace();
        }
        return connection;
    }
}