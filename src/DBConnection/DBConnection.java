package DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    private static Connection conn;

    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/library";
    private static final String USER = "root";
    private static final String PASS = "";

    public static Connection getConnection() {

        //singleton in connection
        if(conn == null){
            try {
                // register driver
                Class.forName(JDBC_DRIVER);
                conn = DriverManager.getConnection(DB_URL, USER, PASS);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return conn;
    }
}
