package PreparedStatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
            String query = "UPDATE students1 SET name = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,"Amit");
            preparedStatement.setInt(2,1);

            int rowAffected = preparedStatement.executeUpdate(); // Same used for Inserting as well as Update

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
