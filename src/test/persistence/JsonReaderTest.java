package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import model.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonexistentFile() {
        JsonReader reader = new JsonReader("./data/nonexistentFile.json");
        try {
            ArrayList<Grocery> groceries = reader.readGroceries();
            ArrayList<Category> categories = reader.readCategories();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmpty() {
        JsonReader reader = new JsonReader("./data/testReaderEmpty.json");
        try {
            ArrayList<Grocery> groceries = reader.readGroceries();
            ArrayList<Category> categories = reader.readCategories();
            groceries.isEmpty();
            categories.isEmpty();
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneric() {
        JsonReader reader = new JsonReader("./data/testReaderGeneric.json");
        try {
            BigDecimal priceA = new BigDecimal("0.50");
            BigDecimal priceB = new BigDecimal("1.00");
            ArrayList<Grocery> groceries = reader.readGroceries();
            ArrayList<Category> categories = reader.readCategories();
            checkGrocery("pepsi", priceA, groceries.get(0));
            checkGrocery("dr pepper", priceB, groceries.get(1));
            checkCategory("drinks", groceries, categories.get(0));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
