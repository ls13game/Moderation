package MySQLTest;

/**
 * Created by Oskar
 * on 25.03.2018
 * for Moderation
 * github.com/oskardevkappa/
 */

import UTIL.SECRETS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLConnection {

    private static Connection conn = null;

    // Hostname
    private static String dbHost = "178.251.228.213";

    // Port -- Standard: 3306
    private static String dbPort = "3306";

    // Datenbankname
    private static String database = "moderation";

    // Datenbankuser
    private static String dbUser = SECRETS.PHPUser;

    // Datenbankpasswort
    private static String dbPassword = SECRETS.PHPPW;

    private MySQLConnection() {
        try {


            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection("jdbc:mysql://" + SECRETS.PHPHOST + ":"
                    + dbPort + "/" + database + "?" + "user=" + dbUser + "&"
                    + "password=" + dbPassword);
            System.out.println("MySQL Connectet");

        } catch (ClassNotFoundException e) {
            System.out.println("Treiber nicht gefunden");
        } catch (SQLException e) {
            System.out.println("Connection to database not possible");
            e.printStackTrace();
        }
    }

    public static Connection getInstance() {
        if (conn == null)
            new MySQLConnection();
        System.out.println("MySQL Connectet");
        return conn;
    }
}
