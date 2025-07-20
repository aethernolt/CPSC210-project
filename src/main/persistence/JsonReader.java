package persistence;

import model.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads all saved grocery data from JSON data stored in file
// based on CPSC210 JSONSerializationDemo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads all groceries from file and returns them as an ArrayList;
    // throws IOException if an error occurs reading data from file
    public ArrayList<Grocery> readGroceries() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseGroceries(jsonObject);
    }

    // EFFECTS: reads all categories from file and returns them as an ArrayList;
    // throws IOException if an error occurs reading data from file
    public ArrayList<Category> readCategories() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseCategories(jsonObject);
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
        ArrayList<Grocery> groceries = new ArrayList<Grocery>();
        JSONArray jsonArray = jsonObject.getJSONArray("groceries");

        for (Object json : jsonArray) {
            JSONObject g = (JSONObject) json;
            groceries.add(parsedGrocery(g));
        }

        return groceries;
    }

    // EFFECTS: parses grocery from JSON object and returns it
    private Grocery parsedGrocery(JSONObject json) {
        String name = json.getString("name");
        String priceString = json.getString("price");
        BigDecimal price = new BigDecimal(priceString);

        Grocery g = new Grocery(name, price);

        return g;
    }

    // EFFECTS: parses categories from JSON object and returns it
    private ArrayList<Category> parseCategories(JSONObject jsonObject) {
        ArrayList<Category> categories = new ArrayList<Category>();
        JSONArray jsonArray = jsonObject.getJSONArray("categories");

        for (Object json : jsonArray) {
            JSONObject c = (JSONObject) json;
            categories.add(parsedCategory(c));
        }

        return categories;
    }

    // EFFECTS: parses category from JSON object and returns it
    private Category parsedCategory(JSONObject json) {
        String name = json.getString("category");
        ArrayList<Grocery> groceries = new ArrayList<Grocery>();
        JSONArray jsonArray = json.getJSONArray("groceries");

        for (Object j : jsonArray) {
            JSONObject g = (JSONObject) j;
            groceries.add(parsedGrocery(g));
        }
        
        Category c = new Category(name);
        for (Grocery g : groceries) {
            c.addItem(g);
        }

        return c;
    }
}