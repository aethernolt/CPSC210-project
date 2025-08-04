package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GroceryTest {
    private Grocery testApple;
    private BigDecimal priceA;

    @BeforeEach
    public void runBefore() {
        priceA = new BigDecimal("1.50");
        testApple = new Grocery("1 kg apples", priceA);
    }

    @Test
    void testConstructor() {
        BigDecimal testBigDecimal = new BigDecimal("1.50");
        assertEquals("1 kg apples", testApple.getName());
        assertEquals(testBigDecimal, testApple.getPrice());
    }

    @Test
    void testEditPrice() {
        BigDecimal priceNew = new BigDecimal("1.25");
        testApple.editPrice(priceNew);
        assertEquals(priceNew, testApple.getPrice());
    }

    @Test
    void testMultipleEditPrice() {
        BigDecimal priceNew = new BigDecimal("1.25");
        BigDecimal priceNewer = new BigDecimal("1.35");
        testApple.editPrice(priceNew);
        testApple.editPrice(priceNewer);
        assertEquals(priceNewer, testApple.getPrice());
    }

    @Test
    void testToString() {
        assertEquals("1 kg apples ($1.50)", testApple.toString());
    }

    @Test
    void testComparePrice() {
        BigDecimal priceLow = new BigDecimal("1.00");
        BigDecimal priceEquals = new BigDecimal("1.50");
        BigDecimal priceHigh = new BigDecimal("2.00");
        assertEquals(-1, testApple.comparePrice(priceLow));
        assertEquals(0, testApple.comparePrice(priceEquals));
        assertEquals(1, testApple.comparePrice(priceHigh));
    }

    @Test
    void testEquals() {
        Grocery testApple2 = new Grocery("1 kg apples", priceA);
        Grocery testBanana = new Grocery("1 bunch bananas", priceA);
        BigDecimal priceB = new BigDecimal("2.00");
        Grocery testApple3 = new Grocery("1 kg apples", priceB);
        assertTrue(testApple.equals(testApple2));
        assertFalse(testApple.equals(testBanana));
        assertFalse(testApple.equals(testApple3));
    }
}
