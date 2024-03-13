package ProjectOne;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PreparedStatementDemo {
    /*
    Write a void method that has a PreparedStatement
    Use the PreparedStatement to update your existing developers Table
    The method should continue to loop and receive the required information
    Enter null to stop the loop;
     */
    public void updateTable(){

        boolean continueLoop = true;

        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/developers", "root", "OjayMcGillicutty89??");
            PreparedStatement statement = connection.prepareStatement("INSERT INTO developers VALUES(?, ?, ?, ?)")){
            Scanner scanner = new Scanner(System.in);
            while(continueLoop){
                System.out.println("Please enter developer's name: ");
                String name = scanner.nextLine();
                System.out.println("Please enter developer's age: ");
                String age = scanner.nextLine();
                System.out.println("Please enter developer's location: ");
                String location = scanner.nextLine();
                System.out.println("Please enter developer's skill: ");
                String skill = scanner.nextLine();
                if( (name.equalsIgnoreCase("null") || age.equalsIgnoreCase("null") || location.equalsIgnoreCase("null") || skill.equalsIgnoreCase("null"))) {
                    continueLoop = false;
                    continue;
                }
                statement.setString(1, name);
                statement.setInt(2, Integer.parseInt(age));
                statement.setString(3, location);
                statement.setString(4, skill);
                statement.execute();
            }

        } catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
    }

    public static void main(String[] args) {
        PreparedStatementDemo demo = new PreparedStatementDemo();
        demo.updateTable();
    }
}