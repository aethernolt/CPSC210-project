package model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CategoryTest {

    @BeforeEach
    void runBefore() {
        BigDecimal priceA = new BigDecimal("1.50");
        Grocery testApple = new Grocery(priceA, "1 kg apples");
        BigDecimal priceB = new BigDecimal("2.00");
        Grocery testBanana = new Grocery(priceB, "bunch of bananas");
        Category testCategory = new Category("fruit");
    }

    @Test
    void testConstructor() {
        assertEquals("fruit", testCategory.getName());
    }

    @Test
    void testAddItem() {
        testCategory.addItem(testApple);
        assertEquals(testApple, testCategory.returnGroceries()[0]);
    }

    @Test
    void testAddMultipleItem() {
        testCategory.addItem(testApple);
        testCategory.addItem(testBanana);
        assertEquals(testApple, testCategory.returnGroceries()[0]);
        assertEquals(testBanana, testCategory.returnGroceries()[1]);
    }

    @Test
    void testToString() {
        testCategory.addItem(testApple);
        testCategory.addItem(testBanana);
        assertEquals("category: fruit; items: 1 kg apples ($1.50), bunch of bananas ($2.00)",
                testCategory.toString());
    }
}
