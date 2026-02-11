package PreparedStatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
            String query = "DELETE FROM students1  WHERE id IN (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, 9);
            preparedStatement.setInt(2, 10);
            preparedStatement.setInt(3, 11);
            preparedStatement.setInt(4, 12);

            int rowAffected = preparedStatement.executeUpdate(); // Same used for Inserting as well as Update

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
