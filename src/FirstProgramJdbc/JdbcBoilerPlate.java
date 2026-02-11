package FirstProgramJdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcBoilerPlate {

    private static String url = "jdbc:mysql://127.0.0.1:3306/?user=root";
    private static String username = "root";
    private static String password = "root";

    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}
