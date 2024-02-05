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
    //define general appscript endpoiint
    private static final String APPSCRIPT_URL = "https://script.google.com/macros/s/AKfycbypUY_i51xClSlz284wqvJgmR31KEC6xHQUc5gbaBNhZDr1_eylbE-Ff4AqzjxI69ad3g/exec";
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
                    user = new User(jsonObject.getInt("id"), jsonObject.getString("username"), jsonObject.getString("email"), jsonObject.getString("firstname"), jsonObject.getBoolean("isFingerprintRegistered"));
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
}