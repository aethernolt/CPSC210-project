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
     * EFFECTS: continues to prompt user until user returns a string matching the
     * name of an existing grocery, then returns matching grocery
     */
    private Grocery groceryHandling(String name) {
        while (true) {
            for (Grocery g : groceries) {
                if (name.equals(g.getName())) {
                    return g;
                }
            }
            System.out.print("Grocery not found, please try again: ");
            name = in.next();
        }
    }

    /*
     * EFFECTS: continues to prompt user until user returns a string matching the
     * name of an existing category, then returns matching category
     */
    private Category categoryHandling(String name) {
        while (true) {
            for (Category c : categories) {
                if (name.equals(c.getName())) {
                    return c;
                }
            }
            System.out.print("Category not found, please try again: ");
            name = in.next();
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: goes through new grocery creation process and adds created grocery
     * to ArrayList groceries
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
     * EFFECTS: goes through grocery editing process and edits the grocery selected
     * in ArrayList groceries
     */
    private void comEditGrocery() {
        System.out.print("\nEnter name of grocery to edit: ");
        String name = in.next();
        Grocery g = groceryHandling(name);
        System.out.print("Enter new price for " + g.getName() + ": ");
        String price = in.next();
        g.editPrice(priceHandling(price));
        System.out.println("Successfully set \"" + g.getName() + "\" to $" + g.getPrice() + " :D");
    }

    /*
     * EFFECTS: goes through grocery price comparison process
     */
    private void comCompareGrocery() {
        System.out.print("\nEnter name of grocery to compare to: ");
        String name = in.next();
        Grocery g = groceryHandling(name);
        System.out.print("Enter comparison price for " + g.getName() + ": ");
        String price = in.next();
        int comp = g.comparePrice(priceHandling(price));
        if (comp == 1) {
            System.out.println(g.getName() + " costs less than $" + price);
        } else if (comp == 0) {
            System.out.println(g.getName() + " costs exactly $" + price);
        } else {
            System.out.println(g.getName() + " costs more than $" + price);
        }
    }

    /*
     * EFFECTS: goes through new category creation process and adds created category
     * to ArrayList categories
     */
    private void comNewCategory() {
        System.out.print("\nEnter name of category: ");
        String name = in.next();
        categories.add(new Category(name));
        System.out.println("Successfully added \"" + categories.get(categories.size() - 1).getName() + "\" :D");
    }

    /*
     * EFFECTS: goes through adding grocery to category process and adds specified
     * grocery to specified category
     */
    private void comAddGrocery() {
        System.out.print("\nEnter name of grocery to add: ");
        String groceryName = in.next();
        Grocery g = groceryHandling(groceryName);
        System.out.print("Enter name of category to add \"" + g.getName() + "\" to: ");
        String catName = in.next();
        Category c = categoryHandling(catName);
        c.addItem(g);
        System.out.println("Succesfully added \"" + c.returnGroceries().get(c.returnGroceries().size() - 1).getName() + "\" to " + c.getName() + " :D");
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
        System.out.print("\nEnter name of category to list: ");
        String name = in.next();
        Category c = categoryHandling(name);
        System.out.println(c.toString());
    }
}
