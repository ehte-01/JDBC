package Statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CrudDelete {

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
            String query = "DELETE FROM Students WHERE id = 4";

            int rowAffected = statement.executeUpdate(query); // Same used for Inserting as well as Update

            if(rowAffected > 0){
                System.out.println( "Data has been successfully deleted");
            } else {
                System.out.println( "Data could not be deleted");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}
