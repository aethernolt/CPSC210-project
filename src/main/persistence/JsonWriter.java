package persistence;

import model.*;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;

// Represents a writer that writes JSON representations of grocery data to a file
// based on CPSC210 JSONSerializationDemo
public class JsonWriter {
    private Writer writer;
    private String destination;

    // EFFECTS: constructs writer to destination
    public JsonWriter(String destination) {

    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination cannot be opened
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of categories and groceries to file
    public void write(ArrayList<Category> categories, ArrayList<Grocery> groceries) {
        
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        
    }
}
