package model;

import java.util.ArrayList;

public class Category {
    private String name;
    private ArrayList<String> groceries;

    /*
     * REQUIRES: name is unique from all previously provided category names
     * EFFECTS: sets name to given name
     */
    public Category(String name) {

    }

    public String getName() {
        return name;
    }

    /*
     * MODIFIES: this
     * EFFECTS: adds a grocery item to ArrayList groceries
     */
    public void addItem(Grocery g) {
        
    }

    /*
     * EFFECTS: provides the name of the category followed by a string representation of all grocery items and their
     * prices within the category
     */
    public String toString() {
        return "";
    }
}
