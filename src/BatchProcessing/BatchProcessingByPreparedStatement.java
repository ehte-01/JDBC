package BatchProcessing;

import java.sql.*;
import java.util.Scanner;

public class BatchProcessingByPreparedStatement {
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
            String query = "INSERT INTO schoolstudents(name, age, marks)  VALUES(?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            Scanner sc = new Scanner(System.in);

            while (true) {
                System.out.print("Enter name: ");
                String name = sc.next();
                System.out.print("Enter age: ");
                int age = sc.nextInt();
                System.out.print("Enter marks: ");
                double marks = sc.nextDouble();
                System.out.print("Enter more data(y/N):");
                String choice = sc.next();

                preparedStatement.setString(1, name);
                preparedStatement.setInt(2, age);
                preparedStatement.setDouble(3, marks);

                preparedStatement.addBatch();

                if(choice.toUpperCase().equals("N")){
                    break;
                }
            }

            int[] arr = preparedStatement.executeBatch();

            for(int i = 0; i < arr.length; i++){
                if(arr[i] == 0){
                    System.out.println("Query: "+i+ " not executed Successfully!!");
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}
