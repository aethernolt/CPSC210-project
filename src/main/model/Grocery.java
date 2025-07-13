package model;

import java.math.BigDecimal;

public class Grocery {
    private String name;
    private BigDecimal price;

    /*
     * REQUIRES: price > 0, name is unique from all previously provided grocery
     * names
     * EFFECTS: sets name to given name and price to given price
     */
    public Grocery(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
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
}
