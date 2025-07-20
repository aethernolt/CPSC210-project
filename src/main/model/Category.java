package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Represents a category of grocery objects with a name and ArrayList containing said groceries
public class Category implements Writable {
    private String name; // name
    private ArrayList<Grocery> groceries; // list of groceries in category

    /*
     * REQUIRES: name is unique from all previously provided category names
     * EFFECTS: sets name to given name and initializes groceries as an empty list
     */
    public Category(String name) {
        this.name = name;
        groceries = new ArrayList<Grocery>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Grocery> returnGroceries() {
        return groceries;
    }

    /*
     * MODIFIES: this
     * EFFECTS: adds a Grocery object to ArrayList groceries
     */
    public void addItem(Grocery g) {
        groceries.add(g);
    }

    /*
     * EFFECTS: provides the name of the category followed by a string
     * representation of all Grocery objects and their
     * prices within the category
     */
    public String toString() {
        String out = "category: " + name + "; items:";
        Boolean x = true;
        for (Grocery g : groceries) {
            if (x) {
                out += (" " + g.toString());
                x = false;
            } else {
                out += (", " + g.toString());
            }
        }
        return out;
    }

    // EFFECTS: returns self as JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("category", name);
        json.put("groceries", groceriesToArray());
        return json;
    }

    // EFFECTS: returns list of groceries as a JSON array
    private JSONArray groceriesToArray() {
        JSONArray jsonArray = new JSONArray();

        for (Grocery g : groceries) {
            jsonArray.put(g.toJson());
        }

        return jsonArray;
    }
}
