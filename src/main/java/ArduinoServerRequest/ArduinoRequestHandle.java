package ArduinoServerRequest;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class ArduinoRequestHandle {
    private static final String DOMAIN = "http://192.168.1.11:";
    private static final String PORT = "80/";
    /**
     * Sends a registration request to the server.
     *
     * @param action The action to be performed on the server.
     * @param id The id of the user to be registered.
     * @return A JsonObject representing the server's response.
     *
     * @author Le Trung Vi
     */
    public static JsonObject sendRegister(String action, int id) {
        JsonObject jsonResponse = null;
        try {
            URL url = new URL(DOMAIN+PORT+action);

            // Open a connection to the URL
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // Set the request method to POST
            conn.setRequestMethod("POST");

            // Set the Content-Type to application/json
            conn.setRequestProperty("Content-Type", "application/json; utf-8");

            // Enable input and output streams
            conn.setDoOutput(true);

            // Create a JSON object as a string with the id
            String jsonInputString = String.format("{\"id\":%d}", id);

            // Write the JSON data to the request
            OutputStream os = conn.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
            osw.write(jsonInputString);
            osw.flush();
            osw.close();
            os.close();

            // Get the response code
            int responseCode = conn.getResponseCode();

            // Print the response code
            System.out.println("POST Response Code :  " + responseCode);

            // If the request was successful (HTTP_OK = 200), read the input stream and parse the response
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuffer content = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                System.out.println("Server response: " + content.toString());

                // Close connections
                in.close();
                conn.disconnect();

                // Convert the content to a JSON object
                JsonReader jsonReader = Json.createReader(new StringReader(content.toString()));
                jsonResponse = jsonReader.readObject();
                jsonReader.close();
                return jsonResponse;
            } else {
                System.out.println("POST request did not work.");
                return jsonResponse;

            }

        } catch (Exception e) {
            e.printStackTrace();
            return jsonResponse;
        }
    }
}