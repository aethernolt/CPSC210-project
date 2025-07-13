package model;

import java.util.ArrayList;

public class Category {
    private String name;
    private ArrayList<Grocery> groceries;

    /*
     * REQUIRES: name is unique from all previously provided category names
     * EFFECTS: sets name to given name and initializes groceries as an empty list
     */
    public Category(String name) {
        this.name = name;
        groceries = new ArrayList<Grocery>();
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
        groceries.add(g);
    }

    /*
     * EFFECTS: provides the name of the category followed by a string
     * representation of all Grocery objects and their
     * prices within the category
     */
    public String toString() {
        String out = "category: " + name + "; items:";
        int x = 0;
        for (Grocery g : groceries) {
            if (x == 0) {
                out += (" " + g.toString());
            } else {
                out += (", " + g.toString());
            }
        }
        return out;
    }
}
