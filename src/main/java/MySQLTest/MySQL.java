package MySQLTest;

import UTIL.SECRETS;

import java.sql.*;
import java.util.logging.Logger;

/**
 * Created by Oskar
 * on 19.03.2018
 * for Moderation
 * github.com/oskardevkappa/
 */


public class MySQL {

    private static Connection connection;
    private String host;
    private String port;
    private String user;
    private String password;
    private String database;

    /**
     * @return MySQL connection
     * Use MySQL.getCon() instead
     */
    @Deprecated
    public static Connection getConnection() {
        return connection;
    }

    /**
     * @param host     Host of MySQL server
     * @param port     Port of MySQL server
     * @param user     User of MySQL database
     * @param password Password of MySQL user
     * @param dbname   Name of MySQL database
     */
    public MySQL(String host, String port, String user, String password, String dbname) throws NullPointerException {
        this.host = SECRETS.PHPHOST;
        this.port = SECRETS.PHPPORT;
        this.user = SECRETS.PHPUser;
        this.password = SECRETS.PHPPW;
        this.database = "Test";
    }

    /**
     * @return MySQL connection
     */
    public MySQL connect() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", this.user, this.password);

        } catch (SQLException e) {

        }
        return this;
    }

    public MySQL disconnect() {
        try {
            connection.close();
            System.out.println("disconnected from MYSQL");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this;
    }

    public Connection getCon(){ return this.connection; }

}
