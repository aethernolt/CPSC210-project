package persistence;

import model.*;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;

// Represents a writer that writes JSON representations of grocery data to a file
// based on CPSC210 JSONSerializationDemo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonWriter {
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to destination
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination cannot be
    // opened
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of categories and groceries to file
    public void write(ArrayList<Category> categories, ArrayList<Grocery> groceries) {
        JSONObject json = new JSONObject();
        
        JSONArray jsonArrayG = new JSONArray();
        for (Grocery g : groceries) {
            jsonArrayG.put(g.toJson());
        }
        json.put("groceries", jsonArrayG);

        JSONArray jsonArrayC = new JSONArray();
        for (Category c : categories) {
            jsonArrayC.put(c.toJson());
        }
        json.put("categories", jsonArrayC);

        saveToFile(json.toString());
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}
