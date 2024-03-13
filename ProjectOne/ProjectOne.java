package ProjectOne;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.sql.ResultSet;
import java.sql.Statement;


import static java.sql.DriverManager.getConnection;

public class ProjectOne implements Developers {

    @Override
    public ResultSet getDeveloperInfo() throws Exception {
        return null;
    }

    @Override
    public ResultSet loadDevelopers() {
        Connection connection = null;
        Statement statement = null;

        try {
            // Connect to the database
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/developers", "root", "OjayMcGillicutty89??");

            // Create a table called developers if it doesn't exist
            String createTableQuery = "CREATE TABLE IF NOT EXISTS developers (name Text, age Integer, location Text, skill Text)";
            statement = connection.createStatement();
            statement.executeUpdate(createTableQuery);

            // Read from project.txt and populate the table
            String filePath = "C:\\Users\\Matrix\\IdeaProjects\\ProjectOne\\src\\ProjectOne\\Project.txt";
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim(); //
                line = line.replaceAll("#$", "");
                String[] data = line.split(",");
                String name = data[0].trim();
                int age = Integer.parseInt(data[1].trim());
                String location = data[2].trim();
                String skill = data[3].trim();

                // Insert data into the table
                String insertQuery = "INSERT INTO developers (name, age, location, skill) VALUES (?, ?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
                preparedStatement.setString(1, name);
                preparedStatement.setInt(2, age);
                preparedStatement.setString(3, location);
                preparedStatement.setString(4, skill);
                preparedStatement.executeUpdate();
            }

            // Query statement to fetch the loaded contents
            String selectQuery = "SELECT * FROM developers";
            ResultSet resultSet = statement.executeQuery(selectQuery);

            return resultSet;
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            // Close the connection and statement
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ProjectOne project = new ProjectOne();
        ResultSet resultSet = project.loadDevelopers();
    }
}
