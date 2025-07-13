package model;

public class Grocery {
    private int price;
    private String name;

    /*
     * REQUIRES: price > 0, name is unique from all previously provided grocery names
     * EFFECTS: sets name to given name and price to given price
     */
    public Grocery(int price, int name) {
        
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    /*
     * REQUIRES: newPrice > 0
     * MODIFIES: this
     * EFFECTS: changes price to newPrice
     */
    public int editPrice(int newPrice) {
        return 0;
    }

    /*
     * REQUIRES: x > 0
     * EFFECTS: returns 0 if x < price, 1 if x = price, 2 if x > price
     */
    public int comparePrice(int x) {
        return 0;
    }
}
