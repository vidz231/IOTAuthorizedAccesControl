package Utils;

import Model.User;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
public class AppScriptRequest {
    //define general AppScript endpoint
    private static final String APPSCRIPT_URL = "https://script.google.com/macros/s/AKfycbzMhP5wVxWCZAD69SL99GgfN87ZlMyznR2IebgoviBrJ47cSAYkBCSfmvJ_ctYz7RDL8w/exec";
    /**
     * This method is used to authenticate a user.
     * It sends a POST request to the APPSCRIPT_URL with the username and password as parameters.
     * If the response status is "success", it creates a User object with the returned data.
     * If the response status is not "success" or the response is null or empty, it returns null.
     *
     * @param username The username of the user.
     * @param password The password of the user.
     * @return A User object if the authentication is successful, null otherwise.
     * @throws Exception If an error occurs during the process.
     */
    public static User authenticate(String username,String password) throws Exception {
        URL url = new URL(APPSCRIPT_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestProperty("Content-Type", "application/json; utf-8");
        User user;
        // Set the request method (GET is the default)
        conn.setRequestMethod("POST");

        // Enable input and output streams
        conn.setDoOutput(true);
        conn.setDoInput(true);

        // Allow the request to follow redirects
        conn.setInstanceFollowRedirects(true);

        // Create JSON object as a string
        String jsonInputString = String.format("{\"action\":\"%s\", \"username\":\"%s\", \"password\":\"%s\"}", "login", username, password);

        // Write the JSON data to the request
        OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream(), StandardCharsets.UTF_8);
        writer.write(jsonInputString);
        writer.flush();
        writer.close();

        // Get the response code
        int responseCode = conn.getResponseCode();

        // If the response code is 200 (HTTP_OK), read the response
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            in.close();
            if (response != null && !response.toString().isEmpty()) {
                JsonReader jsonReader = Json.createReader(new StringReader(response.toString()));
                JsonObject jsonObject = jsonReader.readObject();
                jsonReader.close();
                if (jsonObject.getString("status").equals("success")) {
                    user = new User(jsonObject.getInt("id"), jsonObject.getString("username"), jsonObject.getString("email"), jsonObject.getString("firstName"), jsonObject.getInt("isFingerprintRegistered"));
                    return user;
                } else {
                    return null;
                }
            } else {
                System.out.println("Response is null or empty");
                return null;
            }
        } else {
            System.out.println("Post request failed. Response Code: " + responseCode);
        }
        return null;
    }

    /**
     * This method is used to register a new user.
     * It sends a POST request to the APPSCRIPT_URL with the user details as parameters.
     * If the response status is "success", it returns 1, indicating that the registration was successful.
     * If the response status is not "success" or the response is null or empty, it returns 0, indicating that the registration failed.
     *
     * @param user The User object containing the user details.
     * @param password The password of the user.
     * @return An integer indicating the result of the registration (1 for success, 0 for failure).
     * @throws Exception If an error occurs during the process.
     */
    public static int registerUser(User user, String password) throws Exception{
        URL url = new URL(APPSCRIPT_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestProperty("Content-Type", "application/json; utf-8");
        // Set the request method (GET is the default)
        conn.setRequestMethod("POST");

        // Enable input and output streams
        conn.setDoOutput(true);
        conn.setDoInput(true);

        // Allow the request to follow redirects
        conn.setInstanceFollowRedirects(true);

        // Create JSON object as a string
        String jsonInputString = String.format("{\"action\":\"%s\", \"username\":\"%s\", \"password\":\"%s\", \"email\":\"%s\", \"firstName\":\"%s\"}", "register", user.getUsername(), password, user.getEmail(), user.getFirstname());

        // Write the JSON data to the request
        OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream(), StandardCharsets.UTF_8);
        writer.write(jsonInputString);
        writer.flush();
        writer.close();

        // Get the response code
        int responseCode = conn.getResponseCode();

        // If the response code is 200 (HTTP_OK), read the response
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            in.close();
            if (response != null && !response.toString().isEmpty()) {
                JsonReader jsonReader = Json.createReader(new StringReader(response.toString()));
                JsonObject jsonObject = jsonReader.readObject();
                jsonReader.close();
                if (jsonObject.getString("status").equals("success")) {
                    return 1;
                } else {
                    return 0;
                }
            } else {
                System.out.println("Response is null or empty");
                return 0;
            }
        } else {
            System.out.println("Post request failed. Response Code: " + responseCode);
        }
        return 0;
    }




    public static int registerFinger(JsonObject templateObject,int id) throws Exception{
        URL url = new URL(APPSCRIPT_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestProperty("Content-Type", "application/json; utf-8");
        // Set the request method (GET is the default)
        conn.setRequestMethod("POST");

        // Enable input and output streams
        conn.setDoOutput(true);
        conn.setDoInput(true);

        // Allow the request to follow redirects
        conn.setInstanceFollowRedirects(true);

        // Create JSON object as a string
        String jsonInputString = String.format("{\"action\":\"%s\", \"id\":%d, \"template\":\"%s\"}", "registerFinger",id ,templateObject.getString("fingerprintTemplate"));
        // Write the JSON data to the request
        OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream(), StandardCharsets.UTF_8);

        writer.write(jsonInputString);
        writer.flush();
        writer.close();

        // Get the response code
        int responseCode = conn.getResponseCode();

        // If the response code is 200 (HTTP_OK), read the response
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            System.out.println("Server response: " + response.toString());

            in.close();
            if (response != null && !response.toString().isEmpty()) {
                JsonReader jsonReader = Json.createReader(new StringReader(response.toString()));
                JsonObject jsonObject = jsonReader.readObject();
                jsonReader.close();
                if (jsonObject.getString("status").equals("success")) {
                    return 1;
                } else {
                    return 0;
                }
            } else {
                System.out.println("Response is null or empty");
                return 0;
            }
        } else {
            System.out.println("Post request failed. Response Code: " + responseCode);
        }
        return 0;
    }
}