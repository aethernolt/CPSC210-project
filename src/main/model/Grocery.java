package model;

import org.json.JSONObject;
import persistence.Writable;

import java.math.BigDecimal;

// Represents a grocery with a name and price in dollars
public class Grocery implements Writable {
    private String name; // name
    private BigDecimal price; // price in dollars

    /*
     * REQUIRES: price > 0 and is valid dollar format, name is unique from all
     * previously provided grocery
     * names
     * EFFECTS: sets name to given name and price to given price
     */
    public Grocery(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    /*
     * REQUIRES: newPrice > 0 and is valid dollar format
     * MODIFIES: this
     * EFFECTS: changes price to newPrice
     */
    public void editPrice(BigDecimal newPrice) {
        price = newPrice;
    }

    /*
     * EFFECTS: returns a string representation of the format "name ($price)"
     */
    public String toString() {
        return name + " ($" + price + ")";
    }

    /*
     * REQUIRES: x > 0
     * EFFECTS: returns -1 if x < price, 0 if x = price, 1 if x > price
     */
    public int comparePrice(BigDecimal x) {
        return x.compareTo(price);
    }

    // EFFECTS: returns self as JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("price", String.valueOf(price));
        return json;
    }
}
