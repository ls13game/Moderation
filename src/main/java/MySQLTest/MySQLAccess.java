package MySQLTest;

import UTIL.SECRETS;

import java.sql.*;

/**
 * Created by Oskar
 * on 19.03.2018
 * github.com/oskardevkappa/
 */

public class MySQLAccess {

    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public void readDataBase() throws Exception {

        Class.forName("com.mysql.cj.jdbc.Driver");

        connect = DriverManager.getConnection("jdbc:mysql://" + SECRETS.PHPHOST, SECRETS.PHPUser, SECRETS.PHPPW);
        statement = connect.createStatement();

        resultSet = statement.executeQuery("SELECT * FROM feeback.comments");

        writeResultSet(resultSet);

        preparedStatement = connect.prepareStatement("Test values (Test, ?)");

        preparedStatement.setString(1, "Test");
        preparedStatement.setString(2, "TestEmail");
        preparedStatement.setString(3, "TestWebpage");
        //preparedStatement.setDate(4, new java.sql.Date(2009, 12, 11));
        preparedStatement.setString(5, "TestSummary");
        preparedStatement.setString(6, "TestComment");
        preparedStatement.executeUpdate();

        preparedStatement = connect
                .prepareStatement("SELECT myuser, webpage, datum, summary, COMMENTS from feedback.comments");
        resultSet = preparedStatement.executeQuery();
        writeResultSet(resultSet);

        preparedStatement = connect
                .prepareStatement("delete from feedback.comments where myuser= ? ; ");
        preparedStatement.setString(1, "Test");
        preparedStatement.executeUpdate();

        resultSet = statement
                .executeQuery("select * from feedback.comments");
        writeMetaData(resultSet);

    }

    private void writeMetaData(ResultSet resultSet) throws SQLException {

        System.out.println("The columns in the table are: ");

        System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
        for  (int i = 1; i<= resultSet.getMetaData().getColumnCount(); i++){
            System.out.println("Column " +i  + " "+ resultSet.getMetaData().getColumnName(i));
        }
    }

    private void writeResultSet(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {

            String user = resultSet.getString("myuser");
            String website = resultSet.getString("webpage");
            String summary = resultSet.getString("summary");
            Date date = resultSet.getDate("datum");
            String comment = resultSet.getString("comments");
            System.out.println("User: " + user);
            System.out.println("Website: " + website);
            System.out.println("summary: " + summary);
            System.out.println("Date: " + date);
            System.out.println("Comment: " + comment);
        }
    }

    // Close result set
    private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {

        }
    }

}
