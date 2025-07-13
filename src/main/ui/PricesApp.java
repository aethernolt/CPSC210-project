package ui;

import model.*;

import java.util.Scanner;
import java.util.ArrayList;
import java.math.BigDecimal;

// Grocery price logging, categorizing, and consulting application
public class PricesApp {
    private Scanner in;
    private ArrayList<Grocery> groceries;
    private ArrayList<Category> categories;

    // EFFECTS: runs the prices application
    public PricesApp() {
        runApp();
    }

    /*
     * MODIFIES: this
     * EFFECTS: processes user input
     */
    // based on example program TellerApp method runTeller
    private void runApp() {
        boolean live = true;
        String command = null;

        init();

        while (live) {
            showOptions();
            command = in.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                live = false;
            } else {
                doCommand(command);
            }
        }

        System.out.println("\nShutting down :/");
    }

    /*
     * MODIFIES: this
     * EFFECTS: translates command strings to methods
     */
    private void doCommand(String command) {
        if (command.equals("g")) {
            comNewGrocery();
        } else if (command.equals("e")) {
            comEditGrocery();
        } else if (command.equals("comp")) {
            comCompareGrocery();
        } else if (command.equals("c")) {
            comNewCategory();
        } else if (command.equals("add")) {
            comAddGrocery();
        } else if (command.equals("list")) {
            comListGroceries();
        } else if (command.equals("listc")) {
            comListCategory();
        } else if (command.equals("listc g")) {
            comListCategoryGroceries();
        } else {
            System.out.println("Invalid command.");
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: initializes user input system
     */
    private void init() {
        groceries = new ArrayList<Grocery>();
        categories = new ArrayList<Category>();
        in = new Scanner(System.in);
        in.useDelimiter("\\R");
    }

    /*
     * EFFECTS: shows user list of options
     */
    private void showOptions() {
        System.out.println("\nPlease select one of:");
        System.out.println("\tg -> create a new grocery");
        System.out.println("\te -> edit the price of a grocery");
        System.out.println("\tcomp -> compare a grocery's price to a given price");
        System.out.println("\tc -> create a new category");
        System.out.println("\tadd -> add a grocery to a category");
        System.out.println("\tlist -> list all groceries (and their prices)");
        System.out.println("\tlistc -> list all categories");
        System.out.println("\tlistc g -> list all groceries (and their prices) in a category");
        System.out.println("\tq -> quit");
    }

    /*
     * EFFECTS: continues to prompt user until user returns a string that can be
     * converted to a BigDecimal price, then returns price as a BigDecimal
     */
    private BigDecimal priceHandling(String price) {
        try {
            BigDecimal convert = new BigDecimal(price);
            if (convert.scale() == 2) {
                return convert;
            } else {
                System.out.print("Please enter a grocery price with exactly two digits after the decimal: ");
                price = in.next();
                return priceHandling(price);
            }
        } catch (NumberFormatException e) {
            System.out.print("Please enter a valid grocery price: ");
            price = in.next();
            return priceHandling(price);
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: goes through new grocery creation process
     */
    private void comNewGrocery() {
        System.out.print("\nEnter name of grocery: ");
        String name = in.next();
        System.out.print("Enter price of grocery: ");
        String price = in.next();
        groceries.add(new Grocery(name, priceHandling(price)));
        System.out.println("Successfully added \"" + groceries.get(groceries.size() - 1).getName() + "\" :D");
    }

    /*
     * MODIFIES: this
     * EFFECTS: goes through grocery editing process
     */
    private void comEditGrocery() {
        System.out.print("\nEnter name of grocery to edit: ");
        String name = in.next();
        Boolean r = true;
        Grocery toEdit = null;
        while (r) {
            for (Grocery g : groceries) {
                if (name == g.getName()) {
                    System.out.println("made it");
                    toEdit = g;
                    break;
                } else {
                    System.out.println("sad");
                }
            }
            System.out.print("Grocery not found, please try again: ");
            name = in.next();
        }
        System.out.print("Enter new price for " + toEdit.getName() + ": ");
        String price = in.next();
        toEdit.editPrice(priceHandling(price));
    }

    /*
     * EFFECTS: goes through grocery price comparison process
     */
    private void comCompareGrocery() {

    }

    /*
     * EFFECTS: goes through new category creation process
     */
    private void comNewCategory() {

    }

    /*
     * EFFECTS: goes through adding grocery to category process
     */
    private void comAddGrocery() {

    }

    /*
     * EFFECTS: lists every grocery and price
     */
    private void comListGroceries() {
        if (groceries.isEmpty()) {
            System.out.println("No groceries :/");
        } else {
            for (Grocery g : groceries) {
                System.out.println(g.toString());
            }
        }
    }

    /*
     * EFFECTS: lists every category
     */
    private void comListCategory() {
        if (categories.isEmpty()) {
            System.out.println("No categories :/");
        } else {
            for (Category c : categories) {
                System.out.println(c.getName());
            }
        }
    }

    /*
     * EFFECTS: goes through selecting a category process then lists all groceries
     * and prices in category
     */
    private void comListCategoryGroceries() {

    }
}
