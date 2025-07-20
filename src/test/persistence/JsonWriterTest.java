package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import model.*;

class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmpty() {
        try {
            ArrayList<Category> testCategories = new ArrayList<Category>();
            ArrayList<Grocery> testGroceries = new ArrayList<Grocery>();
            JsonWriter writer = new JsonWriter("./data/testWriterEmpty.json");
            writer.open();
            writer.write(testCategories, testGroceries);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmpty.json");
            ArrayList<Grocery> groceries = reader.readGroceries();
            ArrayList<Category> categories = reader.readCategories();
            assertEquals(testGroceries, groceries);
            assertEquals(testCategories, categories);
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneric() {
        try {
            BigDecimal priceA = new BigDecimal("1.50");
            Grocery testApple = new Grocery("1 kg apples", priceA);
            BigDecimal priceB = new BigDecimal("2.00");
            Grocery testBanana = new Grocery("bunch of bananas", priceB);
            Category testCategory = new Category("fruit");
            testCategory.addItem(testApple);
            testCategory.addItem(testBanana);
            ArrayList<Category> testCategories = new ArrayList<Category>();
            testCategories.add(testCategory);
            ArrayList<Grocery> testGroceries = new ArrayList<Grocery>();
            testGroceries.add(testApple);
            testGroceries.add(testBanana);
        
            JsonWriter writer = new JsonWriter("./data/testWriterGeneric.json");
            writer.open();
            writer.write(testCategories, testGroceries);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneric.json");
            ArrayList<Grocery> testGroceriesComp = reader.readGroceries();
            checkGrocery("1 kg apples", priceA, testGroceriesComp.get(0));
            checkGrocery("bunch of bananas", priceB, testGroceriesComp.get(1));
            ArrayList<Category> testCategoriesComp = reader.readCategories();
            checkCategory("fruit", testGroceries, testCategoriesComp.get(0));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
