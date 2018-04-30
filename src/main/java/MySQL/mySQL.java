package MySQL;

import UTIL.SECRETS;
import java.sql.*;
/**
 * Created by Oskar
 * on 14.04.2018
 * for Moderation
 * github.com/oskardevkappa/
 */


public class mySQL {

    private static String user = SECRETS.PHPUser;
    private static String pw = SECRETS.PHPPW;
    private static String url = "jdbc:mysql://" + SECRETS.PHPHOST  + ":" + SECRETS.PHPPORT + "/" + SECRETS.PHPDATENBANK;


    public static void test() {
        try {
            Connection conn = DriverManager.getConnection(url, user, pw);
            System.out.println("[MySQL] Connected");

            Statement stmnt = conn.createStatement();

            String sql = "insert into guilds "
                    + "(id, owner_name, owner_discriminator, owner_id, name, users, prefix)"
                    + " values ('123', 'oskar', '1234', '5678', 'oskars guild', '10', '-')";

            stmnt.executeUpdate(sql);

            System.out.println("Insert complete");


        } catch (SQLException e) {
            System.out.println("[MySQL] Connection not possible");
        }
    }


}
