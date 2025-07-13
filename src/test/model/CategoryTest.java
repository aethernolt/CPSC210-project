package model;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CategoryTest {
    private Grocery testApple;
    private Grocery testBanana;
    private Category testCategory;

    @BeforeEach
    void runBefore() {
        BigDecimal priceA = new BigDecimal("1.50");
        Grocery testApple = new Grocery("1 kg apples", priceA);
        BigDecimal priceB = new BigDecimal("2.00");
        Grocery testBanana = new Grocery("bunch of bananas", priceB);
        Category testCategory = new Category("fruit");
    }

    @Test
    void testConstructor() {
        assertEquals("fruit", testCategory.getName());
    }

    @Test
    void testAddItem() {
        testCategory.addItem(testApple);
        assertEquals(testApple, testCategory.returnGroceries().get(0));
    }

    @Test
    void testAddMultipleItem() {
        testCategory.addItem(testApple);
        testCategory.addItem(testBanana);
        assertEquals(testApple, testCategory.returnGroceries().get(0));
        assertEquals(testBanana, testCategory.returnGroceries().get(1));
    }

    @Test
    void testToString() {
        testCategory.addItem(testApple);
        testCategory.addItem(testBanana);
        assertEquals("category: fruit; items: 1 kg apples ($1.50), bunch of bananas ($2.00)",
                testCategory.toString());
    }
}
