package Statement;

import java.sql.*;

public class CrudCreate {

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

            String query = String.format("INSERT INTO Students (name, age, marks) VALUES ('%s', %d, %f)", "Rahul",23, 74.5);

            int rowsAffected = statement.executeUpdate(query);

            if (rowsAffected > 0) {
              System.out.println( "Data has been successfully inserted");
            } else {
             System.out.println( "Data could not be inserted");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
