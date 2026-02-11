package PreparedStatement;

import java.sql.*;

public class CrudInsert {

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
            String query = "INSERT INTO students1(name, age, marks) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "John");
            preparedStatement.setInt(2, 30);
            preparedStatement.setDouble(3, 84.7);

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
