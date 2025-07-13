package model;

import java.math.BigDecimal;

public class Grocery {
    private BigDecimal price;
    private String name;

    /*
     * REQUIRES: price > 0, name is unique from all previously provided grocery names
     * EFFECTS: sets name to given name and price to given price
     */
    public Grocery(BigDecimal price, String name) {
        
    }

    /*
     * EFFECTS: returns price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /*
     * EFFECTS: returns name
     */
    public String getName() {
        return name;
    }

    /*
     * REQUIRES: newPrice > 0
     * MODIFIES: this
     * EFFECTS: changes price to newPrice
     */
    public BigDecimal editPrice(BigDecimal newPrice) {
        return null;
    }

    /*
     * EFFECTS: returns a string representation of the format "name ($price)"
     */
    public String toString() {
        return "";
    }

    /*
     * REQUIRES: x > 0
     * EFFECTS: returns 0 if x < price, 1 if x = price, 2 if x > price
     */
    public int comparePrice(BigDecimal x) {
        return 0;
    }
}
