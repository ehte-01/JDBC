package JdbcRevision;

import java.sql.*;

public class Insertion {
    public static void main(String[] args) throws ClassNotFoundException {
        String url = "jdbc:mysql://localhost:3306/mydatabase";
        String username = "root";
        String password = "root";
        //String query = "INSERT INTO employees(id,name,job_title,salary) VALUES(4,'Raju','Full Stack Developer',87000)";
        //String query ="DELETE from employees where id=4;";
        String query = "UPDATE employees\n" + "SET job_title= 'Full stack Developer',salary=70000.0\n"+
                "WHERE id=2;";
        String selectQuery = "SELECT * From employees";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Drivers loaded successfully!!");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {
            Connection con = DriverManager.getConnection(url, username, password);
            System.out.println("Connection established successfully");
            Statement stmt = con.createStatement();
            int rowsaffected = stmt.executeUpdate(query);

//            if (rowsaffected > 0) {
//                System.out.println("Insert Successfull" + rowsaffected + "row(s) affected");
//            } else {
//                System.out.println("Insertion failed!");
//            }
//            if (rowsaffected > 0) {
//                System.out.println("Deletion Successfull" + rowsaffected + "row(s) affected");
//            } else {
//                System.out.println("Deletion failed!");
//            }
            if (rowsaffected > 0) {
                System.out.println("Update Successfull" + rowsaffected + "row(s) affected");
            } else {
                System.out.println("Update failed!");
            }
            ResultSet rs = stmt.executeQuery(selectQuery);
            while (rs.next()) {

                System.out.println(
                        rs.getInt("id") + " "
                                + rs.getString("name") + " "
                                + rs.getString("job_title") + " "
                                + rs.getDouble("salary")
                );
            }
            rs.close();
            stmt.close();
            con.close();
            System.out.println();
            System.out.println("Connection closed successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }
}