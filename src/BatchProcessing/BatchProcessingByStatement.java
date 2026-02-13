package BatchProcessing;

import java.sql.*;
import java.util.Scanner;

public class BatchProcessingByStatement {

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
            Scanner sc = new Scanner(System.in);
            while (true) {
                System.out.println("Enter name: ");
                String name = sc.next();
                System.out.println("Enter age: ");
                int age = sc.nextInt();
                System.out.println("Enter marks: ");
                double marks = sc.nextDouble();
                System.out.println("Enter more data(y/N):");
                String choice = sc.next();
                String query = String.format("INSERT INTO schoolstudents(name, age, marks)  VALUES('%s', %d, %f)", name, age, marks);

                statement.addBatch(query);

                if(choice.toUpperCase().equals("N")){
                    break;
                }
            }

            int[] arr = statement.executeBatch();

            for(int i = 0; i < arr.length; i++){
                if(arr[i] == 1){
                    System.out.println("Query: "+i+ " not executed Successfully!!");
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}
