package persistence;

import model.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads all saved grocery data from JSON data stored in file
// based on CPSC210 JSONSerializationDemo
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads all groceries from file and returns them as an ArrayList;
    // throws IOException if an error occurs reading data from file
    public ArrayList<Grocery> readGroceries() throws IOException {
        return null;
    }

    // EFFECTS: reads all categories from file and returns them as an ArrayList;
    // throws IOException if an error occurs reading data from file
    public ArrayList<Category> readCategories(ArrayList<Grocery> groceries) throws IOException {
        return null;
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }  

    // EFFECTS: parses groceries from JSON object and returns it
    private ArrayList<Grocery> parseGroceries(JSONObject jsonObject) {
        return null;
    }

    // EFFECTS: parses categories from JSON object and returns it
    private ArrayList<Category> parseCategory(JSONObject jsonObject) {
        return null;
    }

    // MODIFIES: cat
    // EFFECTS: parses appropriate groceries from JSON object and adds them to category
    private void addGroceries(Category cat, JSONObject jsonObject, ArrayList<Grocery> groceries) {
        
    }

    // MODIFIES: cat
    // EFFECTS: parses grocery from JSON object and adds it to category
    private void addGrocery(Category cat, JSONObject jsonObject, ArrayList<Grocery> groceries) {
        
    }
}