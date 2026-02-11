package Statement;

import java.sql.*;

public class PerformingCrudOperation {

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
            // String query = String.format("UPDATE Students SET marks = %f WHERE id = %d", 89.5, 3);
            // String query = String.format("INSERT INTO Students (name, age, marks) VALUES ('%s', %d, %f)", "Rahul",23, 74.5);  (INSERTING)
            // String query = "select * from Students";                        (RETRIEVING)
            // ResultSet resultSet = statement.executeQuery(query);

            int rowAffected = statement.executeUpdate(query); // Same used for Inserting as well as Update

            if(rowAffected > 0){
//              System.out.println( "Data has been successfully inserted");
//              System.out.println( "Data has been successfully updated");
                System.out.println( "Data has been successfully deleted");
            } else {
//              System.out.println( "Data could not be inserted");
//              System.out.println( "Data could not be updated");
                System.out.println( "Data could not be deleted");
            }

//            while (resultSet.next()) {      (Creating Table part)
//                int id = resultSet.getInt("id");
//                String name = resultSet.getString("name");
//                int age = resultSet.getInt("age");
//                double marks = resultSet.getDouble("marks");
//                System.out.println("ID: " + id);
//                System.out.println("Name: " + name);
//                System.out.println("Age: " + age);
//                System.out.println("Marks: " + marks);
//            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}
