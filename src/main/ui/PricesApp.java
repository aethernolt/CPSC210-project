package ui;

import model.*;

import java.util.Scanner;
import java.util.ArrayList;
import java.math.BigDecimal;

// Grocery price logging, categorizing, and consulting application
public class PricesApp {
    private Scanner in;
    private ArrayList<Grocery> groceries;

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
        System.out.println("\tlist -> list all groceries (and their prices) in a category");
        System.out.println("\tq -> quit");
    }

    /*
     * MODIFIES: this
     * EFFECTS: goes through new grocery creation process
     */
    private void comNewGrocery() {
        System.out.print("Enter name of grocery: ");
        String name = in.next();
        System.out.print("Enter price of grocery: ");
        String price = in.next();
        Boolean r = true;
        while (r) {
            try {
                while (r) {
                    BigDecimal priceConverted = new BigDecimal(price);
                    if (priceConverted.scale() == 2) {
                        groceries.add(new Grocery(name, new BigDecimal(price)));
                        r = false;
                        break;}
                    System.out.print("Please enter a grocery price with exactly two digits after the decimal: ");
                    price = in.next();
                }
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid grocery price: ");
                price = in.next();
            }
        }
        System.out.println("Successfully added \"" + groceries.get(groceries.size() - 1).getName() + "\" :D");
    }

    /*
     * MODIFIES: this
     * EFFECTS: goes through grocery editing process
     */
    private void comEditGrocery() {

    }

    /*
     * EFFECTS: goes through grocery editing process
     */
    private void comCompareGrocery() {

    }

    /*
     * EFFECTS: goes through grocery editing process
     */
    private void comNewCategory() {

    }

    /*
     * EFFECTS: goes through grocery editing process
     */
    private void comAddGrocery() {

    }

    /*
     * EFFECTS: goes through grocery editing process
     */
    private void comListGroceries() {

    }
}
