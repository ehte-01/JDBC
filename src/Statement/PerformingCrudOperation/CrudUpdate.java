package Statement.PerformingCrudOperation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CrudUpdate {
    private static String url = "jdbc:mysql://127.0.0.1:3306/mydb";
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
            String query = String.format("UPDATE Students SET marks = %f WHERE id = %d", 79.5, 3);

            int rowAffected = statement.executeUpdate(query); // Same used for Inserting as well as Update

            if(rowAffected > 0){
                 System.out.println( "Data has been successfully updated");
            } else {
                 System.out.println( "Data could not be updated");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}
