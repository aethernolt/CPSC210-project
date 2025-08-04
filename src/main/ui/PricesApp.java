package ui;

import model.*;
import persistence.*;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Grocery price logging, categorizing, and consulting application
public class PricesApp {
    private static final String JSON_SAVE = "./data/save_data.json";
    private JFrame f;
    private Scanner in;
    private ArrayList<Grocery> groceries;
    private ArrayList<Category> categories;
    private JsonWriter writer;
    private JsonReader reader;

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
                quit("command");
                live = false;
            } else {
                doCommand(command);
            }
        }
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
        } else if (command.equals("save")) {
            comSave();
        } else if (command.equals("load")) {
            comLoad();
        } else if (command.equals("gV")) {
            comNewGroceryV();
        } else if (command.equals("cV")) {
            comNewCategoryV();
        } else if (command.equals("addV")) {
            comAddGroceryV();
        } else if (command.equals("listV")) {
            comListGroceriesV();
        } else if (command.equals("listcV")) {
            comListCategoryV();
        } else if (command.equals("listc gV")) {
            comListCategoryGroceriesV();
        } else {
            System.out.println("Invalid command.");
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: runs through the shutdown process
     */
    private void quit(String c) {
        if (c.equals("command")) {
            checkSaved();
        } else if (c.equals("visual")) {
            checkSavedV();
        }
    }

    /*
     * EFFECTS: checks if user has saved their data before quitting, prompts to if
     * not
     */
    private void checkSaved() {
        if (!savedComp()) {
            System.out.println("You have not saved your data. Would you like to? (Y/N)");
            String command = in.next();
            Boolean cont = true;
            while (cont) {
                if (command.equals("Y")) {
                    comSave();
                    cont = false;
                } else if (command.equals("N")) {
                    cont = false;
                } else {
                    System.out.println("Please enter Y/N");
                    command = in.next();
                }
            }
        }
        System.out.println("\nShutting down :/");
        System.exit(0);
    }

    /*
     * EFFECTS: checks if user has saved their data before quitting, prompts to if
     * not for visualUI
     */
    private void checkSavedV() {
        if (!savedComp()) {
            String message = "You have not saved your data. Would you like to? (Y/N)";
            String title = "Quit";
            int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION) {
                comSave();
                System.exit(0);
            } else {
                System.exit(0);
            }
        } else {
            System.exit(0);
        }
    }

    /*
     * EFFECTS: returns if saved file is identical to current state
     */
    private Boolean savedComp() {
        ArrayList<Grocery> groSaved = new ArrayList<Grocery>();
        ArrayList<Category> catSaved = new ArrayList<Category>();

        try {
            groSaved = reader.readGroceries();
            catSaved = reader.readCategories();
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_SAVE);
            return false;
        }

        Boolean out = true;

        if (!(groSaved.size() == groceries.size() && catSaved.size() == categories.size())) {
            out = false;
            return out;
        }

        if (!(groceries.toString().equals(groSaved.toString()) && categories.toString().equals(catSaved.toString()))) {
            out = false;
        }

        return out;
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
        writer = new JsonWriter(JSON_SAVE);
        reader = new JsonReader(JSON_SAVE);
        initUI();
    }

    /*
     * MODIFIES: this
     * EFFECTS: initializes ui
     */
    private void initUI() {
        f = new JFrame("Grocery Prices App");
        f.setSize(500, 500);
        f.setLayout(new GridLayout(4, 2, 20, 20));
        f.setVisible(true);

        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                quit("visual");
            }
        });
        f.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        initButtons();
    }

    /*
     * MODIFIES: this
     * EFFECTS: initializes buttons
     */
    @SuppressWarnings("methodlength")
    private void initButtons() {
        JButton buttonNewG = new JButton("New Grocery");
        buttonNewG.setActionCommand("g");
        buttonNewG.addActionListener(new ButtonClickListener());
        f.add(buttonNewG);

        JButton buttonViewG = new JButton("View All Groceries");
        buttonViewG.setActionCommand("list");
        buttonViewG.addActionListener(new ButtonClickListener());
        f.add(buttonViewG);

        JButton buttonAddG = new JButton("Add Grocery to Category");
        buttonAddG.setActionCommand("add");
        buttonAddG.addActionListener(new ButtonClickListener());
        f.add(buttonAddG);

        JButton buttonViewC = new JButton("View All Categories");
        buttonViewC.setActionCommand("listc");
        buttonViewC.addActionListener(new ButtonClickListener());
        f.add(buttonViewC);

        JButton buttonAddC = new JButton("New Category");
        buttonAddC.setActionCommand("c");
        buttonAddC.addActionListener(new ButtonClickListener());
        f.add(buttonAddC);

        JButton buttonViewCG = new JButton("View Groceries in a Category");
        buttonViewCG.setActionCommand("listc g");
        buttonViewCG.addActionListener(new ButtonClickListener());
        f.add(buttonViewCG);

        JButton buttonLoad = new JButton("Load");
        buttonLoad.setActionCommand("load");
        buttonLoad.addActionListener(new ButtonClickListener());
        f.add(buttonLoad);

        JButton buttonSave = new JButton("Save");
        buttonSave.setActionCommand("save");
        buttonSave.addActionListener(new ButtonClickListener());
        f.add(buttonSave);
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (command.equals("g")) {
                doCommand("gV");
            } else if (command.equals("add")) {
                doCommand("addV");
            } else if (command.equals("c")) {
                doCommand("cV");
            } else if (command.equals("list")) {
                doCommand("listV");
            } else if (command.equals("listc")) {
                doCommand("listcV");
            } else if (command.equals("listc g")) {
                doCommand("listc gV");
            } else if (command.equals("load")) {
                doCommand("load");
            } else if (command.equals("save")) {
                doCommand("save");
            }
        }
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
        System.out.println("\tsave -> save current grocery data");
        System.out.println("\tload -> load existing grocery data");
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
     * EFFECTS: goes through new grocery creation process and adds created grocery
     * to ArrayList groceries for visual UI
     */
    private void comNewGroceryV() {
        String name = (String) JOptionPane.showInputDialog(
                f,
                "Enter grocery name",
                "Input",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                "1 kg apples");
        String price = (String) JOptionPane.showInputDialog(
                f,
                "Enter grocery price",
                "Input",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                "1.00");
        BigDecimal convert = new BigDecimal(price);
        groceries.add(new Grocery(name, convert));
    }

    /*
     * MODIFIES: this
     * EFFECTS: goes through grocery editing process and edits the grocery selected
     * in ArrayList groceries
     */
    private void comEditGrocery() {
        if (groceries.isEmpty()) {
            System.out.println("\nNo groceries to edit :/");
        } else {
            System.out.print("\nEnter name of grocery to edit: ");
            String name = in.next();
            Grocery g = groceryHandling(name);
            System.out.print("Enter new price for " + g.getName() + ": ");
            String price = in.next();
            g.editPrice(priceHandling(price));
            System.out.println("Successfully set \"" + g.getName() + "\" to $" + g.getPrice() + " :D");
        }
    }

    /*
     * EFFECTS: goes through grocery price comparison process
     */
    private void comCompareGrocery() {
        if (groceries.isEmpty()) {
            System.out.println("\nNo groceries to compare to :/");
        } else {
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
    }

    /*
     * MODIFIES: this
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
     * MODIFIES: this
     * EFFECTS: goes through new category creation process and adds created category
     * to ArrayList categories for visual UI
     */
    private void comNewCategoryV() {
        String name = (String) JOptionPane.showInputDialog(
                f,
                "Enter category name",
                "Input",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                "Fruits");
        categories.add(new Category(name));
    }

    /*
     * MODIFIES: this
     * EFFECTS: goes through adding grocery to category process and adds specified
     * grocery to specified category
     */
    private void comAddGrocery() {
        if (groceries.isEmpty()) {
            System.out.println("\nNo groceries to add :/");
        } else if (categories.isEmpty()) {
            System.out.println("\nNo categories to add to :/");
        } else {
            System.out.print("\nEnter name of grocery to add: ");
            String groceryName = in.next();
            Grocery g = groceryHandling(groceryName);
            System.out.print("Enter name of category to add \"" + g.getName() + "\" to: ");
            String catName = in.next();
            Category c = categoryHandling(catName);
            c.addItem(g);
            System.out
                    .println("Succesfully added \"" + c.returnGroceries().get(c.returnGroceries().size() - 1).getName()
                            + "\" to " + c.getName() + " :D");
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: goes through adding grocery to category process and adds specified
     * grocery to specified category for visual UI
     */
    private void comAddGroceryV() {
        if (groceries.isEmpty()) {
            System.out.println("\nNo groceries to add :/");
        } else if (categories.isEmpty()) {
            System.out.println("\nNo categories to add to :/");
        } else {
            String grocery = (String) JOptionPane.showInputDialog(
                    f,
                    "Enter grocery name",
                    "Input",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    null,
                    "1 kg apples");
            String category = (String) JOptionPane.showInputDialog(
                    f,
                    "Enter category name",
                    "Input",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    null,
                    "Fruits");
            Grocery g = groceryHandling(grocery);
            Category c = categoryHandling(category);
            c.addItem(g);
        }
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
     * EFFECTS: lists every grocery and price for visual UI
     */
    private void comListGroceriesV() {
        JDialog out = new JDialog(f, "Groceries");
        if (groceries.isEmpty()) {
            out.add(new JLabel("No groceries :/"));
        } else {
            for (Grocery g : groceries) {
                out.add(new JLabel(g.toString()));
            }
        }
        out.setSize(400,400);
        out.setVisible(true);
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
     * EFFECTS: lists every category for visual UI
     */
    private void comListCategoryV() {
        JDialog out = new JDialog(f, "Categories");
        if (categories.isEmpty()) {
            out.add(new JLabel("No categories :/"));
        } else {
            for (Category c : categories) {
                out.add(new JLabel(c.getName()));
            }
        }
        out.setSize(400,400);
        out.setVisible(true);
    }

    /*
     * EFFECTS: goes through selecting a category process then lists all groceries
     * and prices in category
     */
    private void comListCategoryGroceries() {
        if (categories.isEmpty()) {
            System.out.println("\nNo categories :/");
        } else {
            System.out.print("\nEnter name of category to list: ");
            String name = in.next();
            Category c = categoryHandling(name);
            System.out.println(c.toString());
        }
    }

    /*
     * EFFECTS: goes through selecting a category process then lists all groceries
     * and prices in category for visual UI
     */
    private void comListCategoryGroceriesV() {
        JDialog out = new JDialog(f, "Category");
        if (categories.isEmpty()) {
            out.add(new JLabel("\nNo categories :/"));
        } else {
            String category = (String) JOptionPane.showInputDialog(
                    f,
                    "Enter category name",
                    "Input",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    null,
                    "Fruits");
            Category c = categoryHandling(category);
            out.add(new JLabel(c.toString()));
        }
        out.setSize(400,400);
        out.setVisible(true);
    }

    /*
     * EFFECTS: creates a JSON file with current grocery data
     */
    private void comSave() {
        try {
            writer.open();
            writer.write(categories, groceries);
            writer.close();
            System.out.println("Saved your data to " + JSON_SAVE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_SAVE);
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: replaces all current data with data from a JSON file
     */
    private void comLoad() {
        try {
            groceries = reader.readGroceries();
            categories = reader.readCategories();
            System.out.println("Loaded from " + JSON_SAVE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_SAVE);
        }
    }
}
