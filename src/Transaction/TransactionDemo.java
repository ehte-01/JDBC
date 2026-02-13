package Transaction;

import java.sql.*;
import java.util.Scanner;

public class TransactionDemo {

    private static String url ="jdbc:mysql://127.0.0.1:3306/Transaction";
    private static String username ="root";
    private static String password ="root";

    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
            connection.setAutoCommit(false);

            String debit_query = " UPDATE accounts SET balance = balance - ? WHERE account_number = ?";
            String credit_query = " UPDATE accounts SET balance = balance + ? WHERE account_number = ?";

            PreparedStatement debitPreparedStatement = connection.prepareStatement(debit_query);
            PreparedStatement creditPreparedStatement = connection.prepareStatement(credit_query);

            Scanner sc = new Scanner(System.in);
            System.out.print("Enter Account Number : ");
            int account_number = sc.nextInt();
            System.out.print("Enter Amount: ");
            double amount = sc.nextDouble();

            // balance check before debit
            if(!isSufficient(connection, account_number, amount)){
                System.out.println("Transaction Failed !!");
                connection.rollback();
                return;
            }

            // debit
            debitPreparedStatement.setDouble(1,amount);
            debitPreparedStatement.setDouble(2,account_number);
            debitPreparedStatement.executeUpdate();

            // credit
            creditPreparedStatement.setDouble(1,amount);
            creditPreparedStatement.setDouble(2,101);
            creditPreparedStatement.executeUpdate();

            // commit
            connection.commit();
            System.out.println("Transaction Successful");

            debitPreparedStatement.close();
            creditPreparedStatement.close();
            connection.close();
            sc.close();

        } catch (SQLException e) {

                try {
                    if (connection != null) {
                        connection.rollback(); // rollback an exception
                    }

                    connection.close();

                }catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                }
                    System.out.println("Transaction Failed !! Rolled back");
        }

    }

    static boolean isSufficient(Connection connection, int account_number, double amount) {

        try {
            String query = "SELECT balance FROM accounts WHERE account_number = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, account_number);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                double current_balance = resultSet.getDouble("balance");

                return current_balance >= amount ;
            }

            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
