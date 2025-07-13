package model;

import java.util.ArrayList;

public class Category {
    private String name;
    private ArrayList<Grocery> groceries;

    /*
     * REQUIRES: name is unique from all previously provided category names
     * EFFECTS: sets name to given name
     */
    public Category(String name) {

    }

    /*
     * EFFECTS: returns name
     */
    public String getName() {
        return name;
    }

    /*
     * EFFECTS: returns list of Grocery objects
     */
    public ArrayList<Grocery> returnGroceries() {
        return groceries;
    }

    /*
     * MODIFIES: this
     * EFFECTS: adds a Grocery object to ArrayList groceries
     */
    public void addItem(Grocery g) {
        
    }

    /*
     * EFFECTS: provides the name of the category followed by a string representation of all Grocery objects and their
     * prices within the category
     */
    public String toString() {
        return "";
    }
}
