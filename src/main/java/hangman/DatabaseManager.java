package hangman;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.*;
import java.util.Scanner;

// Use JDBC to connect to your database and run queries



public class DatabaseManager {
    static String url = "jdbc:postgresql://localhost:5432/postgres";
    static String user = "postgres";
    static String password1 = "1234";


    static String word;
    public static String getRandomWordFromApi ()
    {
        String Url = "https://api.api-ninjas.com/v1/randomword";

        try
        {
            URL url = new URL (Url);

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection ();
            httpURLConnection.setRequestProperty ("X-Api-Key", "TX3SwsH7SXjROvCZ8E7HHQ==QM8C9D3oAIWYliR2");

            if (httpURLConnection.getResponseCode () == HttpURLConnection.HTTP_OK)
            {
                Scanner scanner        = new Scanner (httpURLConnection.getInputStream ());
                String  serverResponse = scanner.nextLine ();

                JSONObject jsonData = new JSONObject (serverResponse);
                if (jsonData.getString ("word").length () <= 7 && jsonData.getString ("word").length ()>=4)
                {
                    word = jsonData.getString("word");
                    System.out.println(word);
                    return word;
                }
                else
                {
                    getRandomWordFromApi ();
                }
            }
        }
        catch (Exception e)
        {
            System.out.println (e.getMessage ());
        }
        return "lion";
    }
    public static void saving_user_info(String name, String username, String password) {
        try (Connection connection = DriverManager.getConnection(url, user, password1)) {
            String sql = "INSERT INTO player (name, user_name, password) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, username);
                preparedStatement.setString(3, password);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

}

    public static boolean check_info(String username, String password) {
        try (Connection connection = DriverManager.getConnection(url, user,password1)) {
            String sql = "SELECT password FROM users WHERE username = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, username);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    String storedPassword = resultSet.getString("password");
                    return storedPassword.equals(password);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // User not found or incorrect password
    }



    public static void main(String[] args){
        getRandomWordFromApi();
    }




}