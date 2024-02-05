package Utils;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

import java.io.StringReader;
/**
 * This class provides utility methods for parsing JSON strings.
 *
 * @author Le Trung Vi
 */
public class JasonParser {
    /**
     * Parses a JSON string and returns a JsonObject.
     *
     * @param json The JSON string to parse.
     * @return A JsonObject representing the parsed JSON string.
     * @throws jakarta.json.JsonException If the input string is not a valid JSON string.
     *
     * @author Le Trung Vi
     */
    public static JsonObject parseJson(String json) {
        JsonReader jsonReader = Json.createReader(new StringReader(json));
        JsonObject jsonObject = jsonReader.readObject();
        jsonReader.close();
        return jsonObject;
    }
}
