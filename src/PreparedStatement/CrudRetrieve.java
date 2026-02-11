package PreparedStatement;

import java.sql.*;

public class CrudRetrieve {

    private static String url = "jdbc:mysql://localhost:3306/mydb";
    private static String user = "root";
    private static String password = "root";

    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {
            Connection connection = DriverManager.getConnection(url, user, password);

            String query = "SELECT marks FROM students1 where id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, 1);

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                double marks = resultSet.getDouble("marks");
                System.out.println("Marks: " +marks);
            } else{
                System.out.println("No marks found");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }
}
